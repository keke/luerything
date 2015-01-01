package net.lueryting.calibre

import java.io.File
import java.util.{EventListener, EventObject}

import net.luerything.calibre.RepositoryEventType

/**
 * @author keke
 */
trait RepositoryListener extends EventListener {
  def update(event: RepositoryEvent)
}

class RepositoryEvent(source: Object, val eventType: RepositoryEventType, val file: File) extends EventObject(source) {

}


