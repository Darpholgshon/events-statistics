package com.pressassociation.events.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

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
public class DataSourceConfiguration {
  protected static final Logger LOG = LoggerFactory.getLogger(DataSourceConfiguration.class);

  @Autowired
  Environment env;

  @Bean
  public DataSource eventsDataSource() {
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
    LOG.info("Create DataSource for identity => {}", env.getProperty("identity.url"));

    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(env.getProperty("identity.driver"));
    dataSource.setUrl(env.getProperty("identity.url"));
    dataSource.setUsername(env.getProperty("identity.user"));
    dataSource.setPassword(env.getProperty("identity.pswd"));

    return dataSource;
  }
}
