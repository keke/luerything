package net.luerything.web.util

import java.{util => ju}

/**
 *
 */

object HistoryQueries {
  private val FindNameAndQueries = """(\w+):(.*)""".r
  private val FindQueries = "([^|=]+)=([^|=]+)".r

  import scala.collection.JavaConverters.asJavaMapConverter

  case class QueryElem(service: String, queries: ju.Map[String, List[String]])

  def apply(query: String): Array[QueryElem] = {
    require(query ne null)
    query.split("&").toList.flatten(createQueryElem).toArray
  }

  private[util] def createQueryElem(part: String): Option[QueryElem] = {
    def createQueries(qs: String) = {
      FindQueries.findAllIn(qs).foldLeft(Map.empty[String, List[String]]) {
        (a, b) =>
          val Array(name, value) = b.split("=")
          a + (name -> (value :: a.getOrElse(name, List.empty[String])))
      }
    }
    part match {
      case FindNameAndQueries(n, qs) =>
        Some(QueryElem(n, createQueries(qs).asJava))
      case _ => None
    }
  }
}