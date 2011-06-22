package net.luerything.doc.neo4j

import java.net.URI
import net.luerything.doc.{Doc, DocDao}
import org.neo4j.graphdb.{Node, GraphDatabaseService}
import scala.util.control.Exception._
import org.slf4j.LoggerFactory

/**
 *
 */

class Neo4jDocDao(gdb: GraphDatabaseService) extends DocDao {
  private val Log = LoggerFactory.getLogger(classOf[Neo4jDocDao])
  private lazy val docIndex = gdb.index.forNodes("Doc")

  import scala.collection.JavaConversions.iterableAsScalaIterable

  def save(doc: Doc) = {
    val tx = gdb.beginTx
    handling(classOf[Exception]) by (x => tx.failure) andFinally (tx.finish) apply {
      val node = docToNode(doc.uri).getOrElse(gdb.createNode)
      node.setProperty("uri", doc.uri.toString)
      node.setProperty("lastModified", doc.lastModified)
      node.setProperty("name", doc.name)
      node.setProperty("ext", doc.ext)
      doc.id = node.getId
      docIndex.add(node, "uri", node.getProperty("uri"))
      docIndex.add(node, "name", node.getProperty("name"))
      docIndex.add(node, "ext", node.getProperty("ext"))
      tx.success
    }
    this
  }

  def getDocByUri(uri: URI) = {
    docToNode(uri).map(nodeToDoc)
  }

  private def docToNode(uri: URI): Option[Node] = {
    val hits = docIndex.get("uri", uri.toString)
    ultimately(hits.close) apply (Option(hits.getSingle))
  }

  private def nodeToDoc(node: Node): Doc = {
    new Doc(node.getId,
      node.getProperty("lastModified").asInstanceOf[Long],
      new URI(node.getProperty("uri").asInstanceOf[String]),
      node.getProperty("name").asInstanceOf[String],
      node.getProperty("ext").asInstanceOf[String])
  }

  def searchByTitle(name: String, offset: Int, length: Int) = {
    val hits = docIndex.query("name", "*" + name + "*")
    ultimately(hits.close) apply {
      hits.drop(offset).take(length).toList.map(nodeToDoc)
    }
  }

  def getDocById(id: Long) = {
    Option(gdb.getNodeById(id)).map(nodeToDoc)
  }
}