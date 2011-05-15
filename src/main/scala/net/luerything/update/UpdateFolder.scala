package net.luerything.update

import java.util.Collection
import net.luerything.doc.{Doc, DocDao}
import org.slf4j.LoggerFactory
import java.io.File
import org.apache.commons.io.{FilenameUtils, DirectoryWalker}
/**
 *
 */

class UpdateFolder(docDao: DocDao) {
  private val Log = LoggerFactory.getLogger(classOf[UpdateFolder])
  val Url = new File("C:\\mydocs\\eBooks").toURI.toURL


  class Walker extends DirectoryWalker[File] {
    private def accept(file: File) = {
      val ext = FilenameUtils.getExtension(file.getName).toLowerCase
      ext == "pdf" || ext == "chm"
    }

    override def handleFile(file: File, depth: Int, results: Collection[File]) {
      if (file.canRead && accept(file)) {
        docDao.getDocByUri(file.toURI) match {
          case Some(r) if r.lastModified != file.lastModified =>
            updateFile(r, file)
            Log.debug("Updated file {}", file.toURI)
          case None =>
            updateFile(Doc(file), file)
            Log.debug("Added a new file {}", file.toURI)
          case _ => // do nothing.
        }
      }
    }

    def start = {
      walk(new File(Url.toURI), null)
    }
  }

  def update = {
    (new Walker).start
  }

  def updateFile(doc: Doc, file: File) = {
    docDao.save(doc)
  }
}