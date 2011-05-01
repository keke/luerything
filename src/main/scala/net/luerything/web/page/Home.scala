package net.luerything.web.page

import org.apache.velocity.app.VelocityEngine
import org.restlet.Request
import org.springframework.context.MessageSource
import java.util.Locale

/**
 *
 */

class Home(val pageName: String, val ve: VelocityEngine, val messageSource: MessageSource) extends Page with CommonModel {
  override def model = {
    val m = super.model
    m ++ Array("bodyPart" -> "_homebody.vt", "headPart" -> "_homehead.vt", "locale" -> Locale.ENGLISH)
  }
}

trait CommonModel extends PageSupport {
  val messageSource: MessageSource

  override abstract def model: Map[String, Object] = {
    Map("ctxRoot" -> Request.getCurrent.getRootRef.getParentRef.toString,
      "messageSource" -> messageSource, "debug" -> true.asInstanceOf[AnyRef])
  }
}