import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Assert;
import org.junit.Test;

import app.HelloAppEngine;
import app.Login;

public class HelloAppEngineTest {

  @Test
  public void test() throws IOException, ServletException {
    MockHttpServletResponse response = new MockHttpServletResponse();
    new Login().doGet(null, response);
    Assert.assertEquals("text/plain", response.getContentType());
    Assert.assertEquals("UTF-8", response.getCharacterEncoding());
    Assert.assertEquals("Hello App Engine!\r\n", response.getWriterContent().toString());
  }
}
