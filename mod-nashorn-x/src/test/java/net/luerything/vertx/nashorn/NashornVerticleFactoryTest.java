package net.luerything.vertx.nashorn;

import org.junit.Test;
import org.vertx.java.core.Vertx;
import org.vertx.java.platform.Container;
import org.vertx.java.platform.Verticle;

import static org.mockito.Mockito.mock;

public class NashornVerticleFactoryTest
{

  private NashornVerticleFactory fixture = new NashornVerticleFactory();

  @Test
  public void testInit() throws Exception
  {
    fixture.init(mock(Vertx.class), mock(Container.class), mock(ClassLoader.class));
    assert fixture.getEngine() != null;
  }

  @Test
  public void testCreateVerticle() throws Exception
  {
    fixture.init(mock(Vertx.class), mock(Container.class), getClass().getClassLoader());
    Verticle verticle = fixture.createVerticle("abc");
    assert verticle != null;
  }

  @Test
  public void testReportException() throws Exception
  {

  }
}