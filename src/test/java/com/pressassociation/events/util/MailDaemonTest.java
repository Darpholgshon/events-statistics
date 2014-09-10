package com.pressassociation.events.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.pressassociation.events.db.model.Statistic;
import com.pressassociation.events.db.model.StatisticBuilder;
import com.pressassociation.test.TestFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ****************************************************************************************
 *
 * @author <a href="ralph.hodgson@pressassociation.com">Ralph Hodgson</a>
 * @since 05/09/2014 15:29
 * <p/>
 * ****************************************************************************************
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MailDaemonTest.class)
@ComponentScan(basePackageClasses = MailDaemon.class)
@Configuration
public class MailDaemonTest {

  @Autowired
  MailDaemon mailDaemon;

  @Test
  public void sendMessage()
          throws Exception {
    mailDaemon.sendMessage("Test Message", "Hello Tester");
  }

  @Test
  public void sendStatisticMessage()
          throws Exception {
    List<Statistic> stats = Lists.newArrayList(
            StatisticBuilder
                    .aStatistic()
                    .withKey("Test Value")
                    .withValue(100)
                    .build()
    );
    mailDaemon.sendMessage("Test Message", FreemarkerUtil.statisticsHTML(stats, new Date()));
  }

  @Test
  public void sendAttachmentMessage()
          throws Exception {
    Map<String, Object> data = Maps.newHashMap();
    data.put("venues", Lists.newArrayList(TestFactory.testVenue()));
    data.put("titles", Lists.newArrayList(TestFactory.testTitle()));

    File f1 = FreemarkerUtil.toFile(data, "venues", FileType.CSV);
    File f2 = FreemarkerUtil.toFile(data, "titles", FileType.CSV);

    mailDaemon.sendMessage("Test Message", "<p>See attached data file</p>", f1, f2);
  }


}
