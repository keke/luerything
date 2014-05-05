package net.luerything.vertx.nashorn;

import org.vertx.java.platform.impl.cli.Starter;

/**
 * @author keke
 */
public class TestJs
{
  public static void main(String... args)
  {
    System.setProperty("vertx.mods", "./build/mods");
    Starter.main(new String[]{"run", "./src/test/js/test.js"});
  }
}
