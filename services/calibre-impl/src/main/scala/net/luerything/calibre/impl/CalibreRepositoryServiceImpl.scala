package net.luerything.calibre.impl

import java.io.{File, FileFilter}

import net.lueryting.calibre.{CalibreEntry, CalibreRepositoryService}
import org.slf4j.LoggerFactory

/**
 * @author keke
 */
class CalibreRepositoryServiceImpl(val root: File) extends CalibreRepositoryService {
  private val log = LoggerFactory.getLogger(classOf[CalibreRepositoryServiceImpl])

  override def getAllEntries(): Stream[CalibreEntry] = {
    root.listFiles(new FileFilter {
      override def accept(pathname: File): Boolean = {
        pathname.isDirectory
      }
    }).flatMap { f =>
      f.listFiles.map(new CalibreEntry(_))
    }.toStream
  }

  override def addWatch(): Unit = ???
}
