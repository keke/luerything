package net.luerything.web.util

import org.specs2.mutable.SpecificationWithJUnit

/**
 *
 */

class HistoryQueriesSpecTest extends SpecificationWithJUnit {
  import scala.collection.JavaConversions._
  "HistoryQueries" should {
    "parse a query : name:a=b|c=d|a=a" in {
      val r = HistoryQueries("name:a=b|c=d|a=a")
      r.size must_== 1
      val e = r(0)
      e.service must_== "name"
      e.queries.size must_== 2
      e.queries("a") must contain("a", "b").only
      e.queries("c") must_== List("d")
    }
    "parse a query : name:a=b&ccc:a=b" in {
      val r = HistoryQueries("name:a=b&ccc:a=b")
      r.size must_== 2
    }
  }
}