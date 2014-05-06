package net.luerything.vertx.nashorn;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.busmods.BusModBase;
import org.vertx.java.platform.PlatformManagerException;

import javax.script.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;

/**
 * @author keke
 */
public class NashornVerticle extends BusModBase
{
  private static final Logger LOG = LoggerFactory.getLogger(NashornVerticle.class);
  private static final String MODULE_HEADER = "(function(require,module){\r\"use strict\";\r";
  private static final String MODULE_FOOTER = "\rif(typeof vertxStop==='function' && module.setVertxStop){ module" +
      ".setVertxStop(vertxStop)}" +
      "\rreturn module;})(__require__(__thisModule__),__thisModule__)";
  public static final String JVERTX = "__jvertx";
  public static final String JCONTAINER = "__jcontainer";
  private String main;
  private ScriptEngine engine;
  private ScriptContext context;
  private ClassLoader cl;
  private RootModule root;
  private HashMap<URI, Module> moduleCache = new HashMap<>();

  public NashornVerticle(final String main, final ScriptEngine engine, final ClassLoader cl)
  {
    this.main = main;
    this.engine = engine;
    this.cl = cl;
  }

  @Override
  public void start()
  {
    super.start();
    context = new SimpleScriptContext();
    Bindings bindings = engine.createBindings();
    context.setBindings(bindings, ScriptContext.ENGINE_SCOPE);
    bindings.put(JVERTX, vertx);
    bindings.put(JCONTAINER, container);
    URL url = cl.getResource("js/__boot__.js");
    if (LOG.isDebugEnabled())
    {
      LOG.debug("To boot engine from: {}", url);
    }
    try
    {
      engine.eval(new InputStreamReader(url.openStream()), context);
    } catch (ScriptException | IOException e)
    {
      throw new PlatformManagerException("Unable to boot nashorn engine", e);
    }
    if (LOG.isDebugEnabled())
    {
      LOG.debug("Nashorn Powered VerticleFactory initialized and booted");
    }
    context.setAttribute("__jverticle__", this, ScriptContext.ENGINE_SCOPE);
    require(main, null);
  }

  public Module require(String name, final Module parent)
  {
    String moduleId = resolveModuleId(name, parent);
    URL url = tryLoad(moduleId);
    if (LOG.isDebugEnabled())
    {
      LOG.debug("To require module={} by parent ={}", moduleId, parent == null ? "ROOT" : parent.getId());
    }
    if (url == null)
    {
      return null;
    }
    try
    {
      URI moduleUri = url.toURI();
      Module module = moduleCache.get(moduleUri);
      if (module != null)
      {
        return module;
      }
      if (parent == null)
      {
        module = new RootModule();
        root = (RootModule) module;
      } else
      {
        module = new Module();
      }
      module.setId(moduleId);
      context.setAttribute("__thisModule__", module, ScriptContext.ENGINE_SCOPE);
      module.setUri(moduleUri);
      String script = MODULE_HEADER + IOUtils.toString(url) + MODULE_FOOTER;
      module = (Module) engine.eval(script, context);
      moduleCache.put(moduleUri, module);
      return module;
    } catch (ScriptException e)
    {
      final String newMessage = e.getMessage().replace("<eval>", name);
      final ScriptException corrected = new ScriptException(newMessage, name, e.getLineNumber(), e.getColumnNumber());
      corrected.setStackTrace(e.getStackTrace());
      throw new PlatformManagerException("Unable to evaluate script=" + name, corrected);
    } catch (IOException e)
    {
      throw new PlatformManagerException("Unable to load [" + name + "] from " + url, e);
    } catch (URISyntaxException e)
    {
      throw new RuntimeException(e);
    } finally
    {
      context.removeAttribute("__thisModule__", ScriptContext.ENGINE_SCOPE);
    }

  }

  @Override
  public void stop()
  {
    if (root.vertxStop != null)
    {
      root.vertxStop.run();
    }
  }

  ScriptContext getContext()
  {
    return context;
  }

  ScriptEngine getEngine()
  {
    return engine;
  }

  public static class RootModule extends Module
  {

    Runnable vertxStop;

    public void setVertxStop(final Runnable vertxStop)
    {
      this.vertxStop = vertxStop;
    }
  }

  private String resolveModuleId(String name, Module parent)
  {
    name = FilenameUtils.removeExtension(name);
    if (parent == null)
    {
      return name;
    }
    if (name.startsWith("."))
    {
      //this is a relative path
      return FilenameUtils.concat(parent.getId(), name);
    } else
    {
      return name;
    }
  }


  private URL tryLoad(String path)
  {
    if (!FilenameUtils.getExtension(path).equals(""))
    {
      return cl.getResource(path);
    }
    //try .js first
    URL url = tryLoad(path + ".js");
    if (url == null)
    {
      url = tryLoad(path + ".coffee");
    }
    return url;
  }
}
