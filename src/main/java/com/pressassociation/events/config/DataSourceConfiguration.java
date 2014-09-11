package com.pressassociation.events.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * ****************************************************************************************
 *
 * @author <a href="ralph.hodgson@pressassociation.com">Ralph Hodgson</a>
 * @since 03/09/2014 10:20
 * <p/>
 * ****************************************************************************************
 */
@Configuration
@ComponentScan(basePackages = "com.pressassociation.events.db")
@PropertySource("classpath:application.properties")
@Profile(value={"integration", "dev"})
public class DataSourceConfiguration implements ApplicationContextAware {
  protected static final Logger LOG = LoggerFactory.getLogger(DataSourceConfiguration.class);

  private ApplicationContext context;

  @Bean
  public DataSource eventsDataSource() {
    Environment env = context.getEnvironment();

    LOG.info("Create DataSource for arts2    => {}", env.getProperty("arts2.url"));

    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(env.getProperty("arts2.driver"));
    dataSource.setUrl(env.getProperty("arts2.url"));
    dataSource.setUsername(env.getProperty("arts2.user"));
    dataSource.setPassword(env.getProperty("arts2.pswd"));

    return dataSource;
  }

  @Bean
  public DataSource identityDataSource() {
    Environment env = context.getEnvironment();

    LOG.info("Create DataSource for identity => {}", env.getProperty("identity.url"));

    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(env.getProperty("identity.driver"));
    dataSource.setUrl(env.getProperty("identity.url"));
    dataSource.setUsername(env.getProperty("identity.user"));
    dataSource.setPassword(env.getProperty("identity.pswd"));

    return dataSource;
  }

  @Bean
  public DataSource quartzDataSource() {
    LOG.info("Create DataSource for quartz => Embedded HSQLDB");

    return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.HSQL)
            .addScript("classpath:database/tables_hsqldb.sql")
            .build();
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext)
          throws BeansException {
    this.context = applicationContext;
  }
}
