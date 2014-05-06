package net.luerything.vertx.nashorn;

import org.slf4j.LoggerFactory;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.logging.Logger;
import org.vertx.java.platform.Container;
import org.vertx.java.platform.PlatformManagerException;
import org.vertx.java.platform.Verticle;
import org.vertx.java.platform.VerticleFactory;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * @author keke
 */
public class NashornVerticleFactory implements VerticleFactory
{
  private static final String NASHORN = "nashorn";
  private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(NashornVerticleFactory.class);
  private ScriptEngine engine;
  private Vertx vertx;
  private Container container;
  private ClassLoader cl;

  @Override
  public void init(final Vertx vertx, final Container container, final ClassLoader cl)
  {
    this.vertx = vertx;
    this.cl = cl;
    this.container = container;
    ScriptEngineManager mgr = new ScriptEngineManager();
    this.engine = mgr.getEngineByName(NASHORN);
    if (engine == null)
    {
      throw new PlatformManagerException("Nashorn engine not found, probably you are not using Java 8 or later");
    }
  }

  @Override
  public Verticle createVerticle(final String main) throws Exception
  {
    if (LOG.isDebugEnabled())
    {
      LOG.debug("To create vertical main={}", main);
    }
    Verticle verticle = new NashornVerticle(main, engine, cl);
    verticle.setVertx(vertx);
    verticle.setContainer(container);
    return verticle;
  }

  @Override
  public void reportException(final Logger logger, final Throwable t)
  {
    logger.error("Exception in Nashorn JavaScript verticle", t);
  }

  @Override
  public void close()
  {

  }

  ScriptEngine getEngine(){
    return engine;
  }
}
