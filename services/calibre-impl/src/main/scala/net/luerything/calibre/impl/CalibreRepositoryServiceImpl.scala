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
  println("Root repisotyr is " + root)
  private val log = LoggerFactory.getLogger(classOf[CalibreRepositoryServiceImpl])
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
      println("Watch thread starting " + watching)
      while (watching.get()) {
        println("to pull events " + watching)
        watchKey.pollEvents().toList.foreach { e =>
          val kind = e.kind()
          log.debug("Repository was updated, kind={}", kind)
          println("Repostory was updated " + kind + ", context=" + e.context())

          if (kind != OVERFLOW) {
            watchListeners.fire().update(new RepositoryEvent(CalibreRepositoryServiceImpl.this,
              RepositoryEventType.Update, e.context().asInstanceOf[Path].toFile))
          }
        }
        if (!watchKey.reset()) {
          println("To set watch false")
          watching.set(false)
        } else
          Thread.sleep(1000)
      }
      println("Watch thread stopped " + watching)
    }
  }, "CalibreRepositoryWatcher")

  watchThread.start()

  override def getAllEntries(): Stream[CalibreEntry] = {
    root.listFiles(new FileFilter {
      override def accept(pathname: File): Boolean = {
        pathname.isDirectory
      }
    }).flatMap { f =>
      f.listFiles.map(new CalibreEntry(_))
    }.toStream
  }

  override def watch(listener: RepositoryListener) = {
    watchListeners.addListener(listener)
  }

  override def stop: Unit = {
    println("To stop repository service")
    watching.set(false)
    watchKey.cancel()
    watchService.close()
  }
}
