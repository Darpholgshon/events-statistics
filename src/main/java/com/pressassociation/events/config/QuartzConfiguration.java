package com.pressassociation.events.config;

import org.apache.camel.CamelContext;
import org.apache.camel.component.quartz2.QuartzComponent;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.Collections;
import java.util.Properties;

/**
 * ****************************************************************************************
 *
 * @author <a href="ralph.hodgson@pressassociation.com">Ralph Hodgson</a>
 * @since 03/09/2014 15:26
 * <p/>
 * ****************************************************************************************
 */
@Configuration
public class QuartzConfiguration {
  protected static final Logger LOG = LoggerFactory.getLogger(QuartzConfiguration.class);

  @Bean
  @DependsOn(value = {"camelContext"})
  public Scheduler quartzScheduler(CamelContext camelContext) {

    LOG.debug("Configuring Quartz Scheduler...");
    SchedulerFactoryBean factory = new SchedulerFactoryBean();
    factory.setAutoStartup(false);
    factory.setOverwriteExistingJobs(true);
    factory.setSchedulerContextAsMap(
            Collections.singletonMap("CamelQuartzCamelContext-camelContext", camelContext));

    Properties props = new Properties();
    props.setProperty("org.quartz.scheduler.skipUpdateCheck", "true");

    factory.setQuartzProperties(props);

    return factory.getObject();
  }

  @Bean
  @DependsOn(value = "quartzScheduler")
  public QuartzComponent quartz2(Scheduler quartzScheduler) {
    QuartzComponent quartzComponent = new QuartzComponent();
    quartzComponent.setScheduler(quartzScheduler);
    return quartzComponent;
  }
}
