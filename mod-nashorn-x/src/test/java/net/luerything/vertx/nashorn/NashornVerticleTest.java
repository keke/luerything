package net.luerything.vertx.nashorn;


import org.junit.Assert;
import org.junit.Test;
import org.vertx.java.core.Vertx;
import org.vertx.java.platform.Container;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class NashornVerticleTest
{
  private NashornVerticleFactory factory = new NashornVerticleFactory();

  @Test
  public void testStart() throws Exception
  {
    factory.init(mock(Vertx.class), mock(Container.class), getClass().getClassLoader());
    NashornVerticle verticle = (NashornVerticle) factory.createVerticle("abc");
    verticle.start();
    assert verticle.getContext().getAttribute(NashornVerticle.JVERTX) != null;
    assert verticle.getContext().getAttribute(NashornVerticle.JCONTAINER) != null;
    assert verticle.getEngine().eval("typeof __require__", verticle.getContext()).equals("function");
  }

  @Test
  public void testRequireNotExist() throws Exception
  {
    ClassLoader mockLoader = mock(ClassLoader.class);
    when(mockLoader.getResource(eq("js/__boot__.js"))).thenReturn(getClass().getClassLoader().getResource
        ("js/__boot__.js"));

    factory.init(mock(Vertx.class), mock(Container.class), mockLoader);
    NashornVerticle verticle = (NashornVerticle) factory.createVerticle("abc");
    verticle.start();
//    reset(mockLoader);
    Assert.assertNull(verticle.require("not_exist", null));
    verify(mockLoader).getResource("not_exist.js");
    verify(mockLoader).getResource("not_exist.coffee");
  }

  @Test
  public void testStop() throws Exception
  {

  }
}