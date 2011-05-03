package net.luerything.web.page

import org.apache.velocity.app.VelocityEngine
import org.restlet.representation.Representation
import org.restlet.ext.velocity.TemplateRepresentation
import org.restlet.{Response, Request, Restlet}
import org.restlet.data.{Method, MediaType}

/**
 * @author keke
 */
trait PageSupport {

  import scala.collection.JavaConverters.asJavaMapConverter

  val ve: VelocityEngine
  val pageName: String
  import scala.collection.mutable.Map
  def model: Map[String, Object] = Map.empty

  def getPageRepresentation: Representation = {
    new TemplateRepresentation(ve.getTemplate(pageName), model.asJava, MediaType.TEXT_HTML)
  }
}

/**
 * @author keke
 */
abstract class Page extends Restlet with PageSupport {
  override def handle(request: Request, response: Response) {
    super.handle(request, response)
    if (request.getMethod == Method.GET) {
      val e = getPageRepresentation
      response.setEntity(e)
    }
  }
}