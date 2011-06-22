package net.luerything.doc

import java.net.URI
import java.io.File
import org.apache.commons.io.FilenameUtils

object Doc {
  def apply(file: File): Doc = new Doc(-1,
    file.lastModified, file.toURI,
    FilenameUtils.getBaseName(file.getName),
    FilenameUtils.getExtension(file.getName).toLowerCase)
}

/**
 *
 */

class Doc(var id: Long, var lastModified: Long,
          val uri: URI, val name: String,
          val ext: String) {
  @transient
  lazy val file = new File(uri)
}