package com.pressassociation.events.route;

import com.pressassociation.events.config.MyCamelConfiguration;
import com.pressassociation.events.config.QuartzConfiguration;
import com.pressassociation.test.MockConfiguration;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.NotifyBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: <a href="ralph.hodgson@pressassociation.com">Ralph Hodgson</a>
 * Date: 27/01/14
 * Time: 10:45
 */
@ContextConfiguration(classes = {QuartzConfiguration.class, MyCamelConfiguration.class, MockConfiguration.class})
@WebAppConfiguration()
@ActiveProfiles("mock")
public class QuartzSchedulerRouteTest extends AbstractJUnit4SpringContextTests {
  protected static final Logger LOG = LoggerFactory.getLogger(QuartzSchedulerRouteTest.class);

  @Autowired
  CamelContext context;

  @Test
  public void quartzTestFiresTwice()
          throws Exception {

    NotifyBuilder notify = new NotifyBuilder(context)
            .fromRoute("ReportScheduleRoute")
            .whenDone(2)
            .create();

    // now we want to wait until quartz does something.
    Assert.assertTrue("Should be done", notify.matches(15, TimeUnit.SECONDS));
  }

  @Test
  public void quartzTestNotFiredEnough()
          throws Exception {

    NotifyBuilder notify = new NotifyBuilder(context)
            .fromRoute("ReportScheduleRoute")
            .whenDone(5)
            .create();

    LOG.debug("Fired {} time(s).", notify.toString());

    // now we want to wait until quartz does something.
    Assert.assertFalse("Shouldn't be done", notify.matches(3, TimeUnit.SECONDS));
  }
}
