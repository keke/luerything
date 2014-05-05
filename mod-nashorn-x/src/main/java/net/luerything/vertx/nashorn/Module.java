package net.luerything.vertx.nashorn;

import java.io.Serializable;
import java.net.URI;

/**
 * @author keke
 */
public class Module implements Serializable
{
  private String id;
  private URI uri;
  private Object exports;

  public Module(final String id)
  {
    this.id = id;
  }

  public Module()
  {
    this(null);
  }

  public String getId()
  {
    return id;
  }

  public void setId(final String id)
  {
    this.id = id;
  }

  public URI getUri()
  {
    return uri;
  }

  public void setUri(final URI uri)
  {
    this.uri = uri;
  }

  public Object getExports()
  {
    return exports;
  }

  public void setExports(final Object exports)
  {
    this.exports = exports;
  }
}
