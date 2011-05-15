package net.luerything.web.resource

import org.restlet.resource.{Post, ServerResource}
import org.restlet.data.Form
import net.luerything.update.UpdateFolder
import net.luerything.web.state.StateService
import java.util.concurrent.{Future, Callable}
import org.slf4j.LoggerFactory

/**
 *
 */

class UpdateResource(update: UpdateFolder, stateService: StateService) extends ServerResource {
  private val Log = LoggerFactory.getLogger(classOf[UpdateResource])
  private val UpdateFuture = "__update_future__"

  @Post
  def updateAll(form: Form) = {
    def submitTask = {
      getApplication.getTaskService.submit(new Callable[Unit] {
        def call() {
          update.update
          Log.info("To remove future")
          stateService.remove(UpdateFuture)
        }
      })
    }
    stateService.get[Future[Unit]](UpdateFuture).filter(f => !f.isDone && !f.isCancelled).getOrElse {
      stateService.add(UpdateFuture, submitTask)
      Log.debug("UpdateFuture added")
    }
    null
  }
}