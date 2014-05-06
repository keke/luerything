package net.luerything.vertx.nashorn;

import java.net.URL;

/**
 * @author keke
 */
public interface ScriptLoader
{
  URL load(String name);
}
