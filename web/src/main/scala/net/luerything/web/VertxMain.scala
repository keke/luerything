package net.luerything.web

import org.vertx.java.busmods.BusModBase
import org.vertx.java.core.AsyncResult
import org.vertx.java.core.AsyncResultHandler
import java.io.File

/**
 */
class VertxMain extends BusModBase {
  /**
   */
  override def start() {
    super.start();    
    var webCfg = getOptionalObjectConfig("webModuleConfig", null)

    container.deployVerticle("net.luerything.web.WebServer", webCfg, 1);
    println("To deploy config service")
    container.deployVerticle("net.luerything.service.impl.ConfigServiceImpl", 1)
  }
}