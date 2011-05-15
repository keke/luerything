package net.luerything.web.resource

import org.restlet.resource.{Get, ServerResource}
import net.luerything.doc.DocDao
import org.json.{JSONArray, JSONObject}

/**
 *
 */

class SearchResource(docDao: DocDao) extends ServerResource {
  @Get("json")
  def search = {
    val queryForm = getRequest.getResourceRef.getQueryAsForm
    val query = queryForm.getFirstValue("q")
    val offset = queryForm.getFirstValue("o", "0").toInt
    val length = queryForm.getFirstValue("l", "20").toInt
    docDao.searchByTitle(query, offset, length).foldLeft(new JSONArray) {
      (a, b) =>
        val o = new JSONObject
        o.put("name", b.name)
        o.put("id", b.id)
        a.put(o)
    }
  }
}