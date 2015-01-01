package net.luerything.calibre.impl

import java.io.{File, FileFilter}
import java.nio.file.{FileSystems, Path}
import java.util.concurrent.atomic.AtomicBoolean

import net.luerything.calibre.RepositoryEventType
import net.lueryting.calibre.{CalibreEntry, CalibreRepositoryService, RepositoryEvent, RepositoryListener}
import org.apache.commons.lang3.event.EventListenerSupport
import org.slf4j.LoggerFactory

/**
 * @author keke
 */
class CalibreRepositoryServiceImpl(val root: File) extends CalibreRepositoryService {
  private val log = LoggerFactory.getLogger(classOf[CalibreRepositoryServiceImpl])
  log.debug("Root folder of calibre repository is {}", root)
  private val watching = new AtomicBoolean(true)
  private val watchService = FileSystems.getDefault.newWatchService()

  import java.nio.file.StandardWatchEventKinds._

  private val watchKey = {
    root.toPath.register(watchService, ENTRY_CREATE,
      ENTRY_DELETE, ENTRY_MODIFY)
  }
  private val watchListeners = EventListenerSupport.create(classOf[RepositoryListener])
  private val watchThread = new Thread(new Runnable {

    import scala.collection.JavaConversions._

    override def run(): Unit = {
      log.info("To start Watch thread for calibre repository at {}", root)
      while (watching.get()) {
        log.debug("To pull events")
        watchKey.pollEvents().toList.foreach { e =>
          val kind = e.kind()
          log.debug("Repository was updated, kind={}", kind)

          if (kind != OVERFLOW) {
            watchListeners.fire().update(new RepositoryEvent(CalibreRepositoryServiceImpl.this,
              RepositoryEventType.Update, e.context().asInstanceOf[Path].toFile))
          }
        }
        if (!watchKey.reset()) {

          watching.set(false)
        } else
          Thread.sleep(1000)
      }
      log.info("Watch thread stopped ")
    }
  }, "CalibreRepositoryWatcher")

  watchThread.start()

  override def getAllEntries(): Stream[CalibreEntry] = {
    root.listFiles(new FileFilter {
      override def accept(pathname: File): Boolean = {
        pathname.isDirectory && !pathname.isHidden
      }
    }).flatMap { f =>
      f.listFiles(new FileFilter {
        override def accept(pathname: File): Boolean = !pathname.isHidden
      }).map(new CalibreEntry(_))
    }.toStream
  }

  override def watch(listener: RepositoryListener) = {
    watchListeners.addListener(listener)
  }

  override def close: Unit = {
    log.info("To stop repository service")
    watching.set(false)
    watchKey.cancel()
    watchService.close()
  }
}
