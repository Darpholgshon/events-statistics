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

import javax.sql.DataSource;
import java.util.Collections;
import java.util.Properties;

import static org.apache.camel.component.quartz2.QuartzConstants.QUARTZ_CAMEL_CONTEXT;

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

  public static final String CONTEXT_NAME = "camel-1";

  @Bean
  @DependsOn(value = {"camelContext", "quartzDataSource"})
  public Scheduler quartzScheduler(CamelContext camelContext, DataSource quartzDataSource)
          throws Exception {
    LOG.debug("Configuring Quartz Scheduler...");
    SchedulerFactoryBean factory = new SchedulerFactoryBean();
    factory.setDataSource(quartzDataSource);
    factory.setAutoStartup(false);
    factory.setOverwriteExistingJobs(true);
    factory.setSchedulerContextAsMap(Collections.singletonMap(QUARTZ_CAMEL_CONTEXT + "-" + CONTEXT_NAME, camelContext));

    Properties props = new Properties();
    props.setProperty("org.quartz.scheduler.instanceName", "QuartzEventsStatisticsScheduler");
    props.setProperty("org.quartz.scheduler.instanceId", "AUTO");
    props.setProperty("org.quartz.scheduler.skipUpdateCheck", "true");
    props.setProperty("org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.StdJDBCDelegate");
    props.setProperty("org.quartz.jobStore.isClustered", "true");
    factory.setQuartzProperties(props);
    factory.afterPropertiesSet();

    return factory.getObject();
  }

  @Bean
  @DependsOn(value = "quartzScheduler")
  public QuartzComponent quartz2(Scheduler quartzScheduler) {
    LOG.debug("Configuring Quartz Component...");
    QuartzComponent quartzComponent = new QuartzComponent();
    quartzComponent.setScheduler(quartzScheduler);
    return quartzComponent;
  }
}
