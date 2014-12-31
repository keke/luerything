package net.lueryting.calibre

import java.io.File

/**
 * Service to access Calibre Repository
 * @author keke
 */
trait CalibreRepositoryService {
  val root: File

  def getAllEntries(): Stream[CalibreEntry]

  def addWatch()
}
