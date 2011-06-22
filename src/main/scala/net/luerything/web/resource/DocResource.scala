package net.luerything.web.resource

import org.restlet.resource.ServerResource
import net.luerything.doc.DocDao
import org.restlet.representation.{Variant, Representation}
import sys.process.Process
import java.io.File

/**
 *
 */

class DocResource(docDao: DocDao) extends ServerResource {

  private lazy val id = getRequestAttributes.get("id").asInstanceOf[String].toInt
  private lazy val optDoc = docDao.getDocById(id)
  private lazy val isOpen = getRequest.getReferrerRef.getQueryAsForm.contains("open")

  override def doInit() {
    setExisting(optDoc.isDefined)
    if (isExisting) {
      if (isOpen) {
        setNegotiated(false)
      }
    }
  }

  override def post(entity: Representation) = {
    if (isOpen) {
      openFile
    }
    null
  }

  override def post(entity: Representation, variant: Variant) = {
    null
  }

  private def openFile = {
    Process("cmd", List("/c", new File(optDoc.get.uri).getAbsolutePath)).run
  }
}