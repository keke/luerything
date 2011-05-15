package net.luerything.web.state.impl

import net.luerything.web.state.StateService
import org.restlet.ext.servlet.ServletUtils
import org.restlet.Request

/**
 *
 */

class SessionStateService extends StateService {
  private def getSession = {
    Some(ServletUtils.getRequest(Request.getCurrent).getSession())
  }

  def remove[T](key: String) = {
    getSession.foreach(_.removeAttribute(key))
    this
  }

  def get[T](key: String) = getSession.flatMap(s => Option(s.getAttribute(key).asInstanceOf[T]))

  def add[T](key: String, value: T) = {
    getSession.map(_.setAttribute(key, value))
    this
  }
}