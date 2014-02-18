package net.luerything.service

trait ConfigService {}

package impl {
  import org.vertx.java.busmods.BusModBase
  import org.vertx.java.core.Handler
  import org.vertx.java.core.eventbus.Message
  import org.vertx.java.core.json.JsonObject

  class ConfigServiceImpl extends BusModBase with ConfigService {
    private lazy val loadHandler = new Handler[Message[JsonObject]] {
      def handle(message: Message[JsonObject]) {
        println("To load config:" + message)
        sendOK(message)
      }
    }
    override def start() {
      super.start
      eb.registerHandler("luerything.config.load", loadHandler)
      println("Config Service started")
    }
  }
}