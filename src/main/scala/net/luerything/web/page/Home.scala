package net.luerything.web.page

import org.apache.velocity.app.VelocityEngine
import org.restlet.{Request, Application}

/**
 *
 */

class Home(val pageName: String, val ve: VelocityEngine) extends Page with DefaultModel {

}

object DefaultModel {
}

trait DefaultModel extends PageSupport {
  override abstract def model: Map[String, Object] = {
    Map("ctxRoot" -> Request.getCurrent.getRootRef.getParentRef.toString)
  }
}