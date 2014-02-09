package net.luerything.web
import org.vertx.java.core.http.RouteMatcher
import org.vertx.mods.web.{ WebServer => WS, WebServerBase }
import org.vertx.java.core.Handler
import org.vertx.java.core.http.HttpServerRequest
import java.io.File
/**
 */
class WebServer extends WS {
  import WebServerBase.DEFAULT_WEB_ROOT
  import WebServerBase.DEFAULT_INDEX_PAGE

  override def routeMatcher() = {
    val webRoot = getOptionalStringConfig("web_root", DEFAULT_WEB_ROOT);
    val index = getOptionalStringConfig("index_page", DEFAULT_INDEX_PAGE);
    val webRootPrefix = webRoot + File.separator;
    val indexPage = webRootPrefix + index;
    var r = super.routeMatcher()
    val handler = new Handler[HttpServerRequest] {
      def handle(req: HttpServerRequest) {
        println("Handle " + req.path)
        req.response().sendFile(indexPage)
      }
    }
    r.getWithRegEx("/note/.*", handler)
    r
  }
}