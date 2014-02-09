package net.luerything.web

import org.vertx.java.busmods.BusModBase
import org.vertx.java.core.AsyncResult
import org.vertx.java.core.AsyncResultHandler
import java.io.File


class VertxMain extends BusModBase {
  override def start() {
    super.start();
    val fs = vertx.fileSystem()
    var webCfg = getOptionalObjectConfig("webModuleCfg", null)

    container.deployModule(getMandatoryStringConfig("webModule"), webCfg, 1, new AsyncResultHandler[String]{
      def handle(result:AsyncResult[String]){

      }
    });
  }
}