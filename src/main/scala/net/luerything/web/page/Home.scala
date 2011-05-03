package net.luerything.web.page

import org.apache.velocity.app.VelocityEngine
import org.restlet.Request
import org.springframework.context.MessageSource
import java.util.Locale
import scala.collection.mutable.Map

/**
 *
 */
trait CommonModel extends PageSupport {
  val messageSource: MessageSource

  override abstract def model: Map[String, Object] = {
    val req = Request.getCurrent
    Map("appRoot" -> req.getRootRef.toString,
      "ctxRoot" -> req.getRootRef.getParentRef.toString,
      "messageSource" -> messageSource, "debug" -> true.asInstanceOf[AnyRef])
  }
}

/**
 *
 */
class Home(val pageName: String, val ve: VelocityEngine,
           val messageSource: MessageSource) extends Page with CommonModel {
  override def model = {
    super.model ++ Array("bodyPart" -> "_homebody.vt", "headPart" -> "_homehead.vt", "locale" -> Locale.ENGLISH)
  }
}