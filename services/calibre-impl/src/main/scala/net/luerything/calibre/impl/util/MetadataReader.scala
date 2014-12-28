package net.luerything.calibre.impl.util

import java.io.File

import org.slf4j.{Logger, LoggerFactory}

/**
 * Calibre net.luerything.calibre.metadata opf reader
 * @author keke
 */
class MetadataReader(file: File) {
  private val log = LoggerFactory.getLogger(classOf[MetadataReader])
  log.debug("To reader file {}", file)

}

object MetadataReader {

}