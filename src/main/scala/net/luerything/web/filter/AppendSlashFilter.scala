package net.luerything.web.filter


import org.restlet.routing.Filter
import org.restlet.{Response, Request, Context, Restlet}
import org.slf4j.LoggerFactory

/**
 *
 */
class AppendSlashFilter(ctx: Context, next: Restlet) extends Filter(ctx, next) {
  private val log = LoggerFactory.getLogger(classOf[AppendSlashFilter])

  override def beforeHandle(request: Request, response: Response) = {
    val ref = request.getResourceRef
    val path = ref.getPath
    if (!path.endsWith("/") && needAppend(path)) {
      ref.setPath(path + "/")
      response.redirectPermanent(ref)
      log.debug("To redirect to {}", ref)
      Filter.SKIP
    } else {
      Filter.CONTINUE
    }
  }

  private def needAppend(path: String) = !path.matches("\\/[^\\/]+\\.[^\\/]+$")
}