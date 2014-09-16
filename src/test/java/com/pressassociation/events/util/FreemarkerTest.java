package com.pressassociation.events.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.pressassociation.test.TestFactory;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * ****************************************************************************************
 *
 * @author <a href="ralph.hodgson@pressassociation.com">Ralph Hodgson</a>
 * @since 15/09/2014 10:11
 * <p/>
 * ****************************************************************************************
 */

public class FreemarkerTest {
  protected static final Logger LOG = LoggerFactory.getLogger(FreemarkerTest.class);

  @Test
  public void testFull()
          throws IOException, TemplateException {
    Map<String, Object> data = Maps.newHashMap();
    data.put("titles", Lists.newArrayList(TestFactory.testTitle()));

    FreemarkerUtil.toHTML(data, "titles");
  }

  @Test
  public void testPartial()
          throws IOException, TemplateException {
    Map<String, Object> data = Maps.newHashMap();
    data.put("titles", Lists.newArrayList(TestFactory.testTitleStub()));

    String html = FreemarkerUtil.toHTML(data, "titles");

    LOG.debug("html: {}", html);
  }


}
