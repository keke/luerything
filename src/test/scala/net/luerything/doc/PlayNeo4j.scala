package net.luerything.doc

import org.neo4j.kernel.EmbeddedGraphDatabase

import scala.util.control.Exception._
/**
 *
 */

object PlayNeo4j extends App {
  val gdb = new EmbeddedGraphDatabase("./data/graph/db")
  ultimately(gdb.shutdown) apply {
    val index = gdb.index.forNodes("Doc")
    val hits = index.query("name", "*java*")
    println(hits.size, index.query("name", "*").size)
  }
}