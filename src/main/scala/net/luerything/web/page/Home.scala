package net.luerything.web.page

import org.apache.velocity.app.VelocityEngine
import org.restlet.Request
import org.springframework.context.MessageSource
import java.util.Locale

/**
 *
 */

class Home(val pageName: String, val ve: VelocityEngine, messageSource: MessageSource) extends Page with DefaultModel {
  override def model = {
    val m = super.model
    m ++ Array("bodyPart" -> "_homebody.vt", "title" -> messageSource.getMessage("home.title", null, Locale.ENGLISH))
  }
}

trait DefaultModel extends PageSupport {
  override abstract def model: Map[String, Object] = {
    Map("ctxRoot" -> Request.getCurrent.getRootRef.getParentRef.toString)
  }
}