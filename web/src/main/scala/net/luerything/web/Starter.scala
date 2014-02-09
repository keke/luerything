package net.luerything.web

import org.vertx.java.platform.PlatformLocator
import org.vertx.java.core.json.JsonObject
import org.vertx.java.core.AsyncResultHandler
import org.vertx.java.core.AsyncResult

/**
 */
object Starter{
  def main(args: Array[String]){
    start(args)
  }

  def start(args: Array[String]){
    val pm = PlatformLocator.factory.createPlatformManager()
    val cfg = new JsonObject()
    pm.deployModule("net.luerything~web~0.0.1-SNAPSHOT", cfg, 1, new AsyncResultHandler[String]{
      def handle(result: AsyncResult[String]){
        
      }
    })
  }
}