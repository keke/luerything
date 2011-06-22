package net.luerything.doc

import java.net.URI

/**
 *
 */

trait DocDao {
  def getDocByUri(uri: URI): Option[Doc]

  def save(doc: Doc): DocDao

  def searchByTitle(name: String, offset: Int, length: Int): List[Doc]

  def getDocById(id: Long): Option[Doc]
}