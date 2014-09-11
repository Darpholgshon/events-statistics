package com.pressassociation.events.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * ****************************************************************************************
 *
 * @author <a href="ralph.hodgson@pressassociation.com">Ralph Hodgson</a>
 * @since 09/09/2014 10:59
 * <p/>
 * ****************************************************************************************
 */
@Configuration
@Profile("production") // running on tomcat instance wrapped in a war
public class JNDIConfiguration {
  @Bean
  public DataSource eventsDataSource()
          throws NamingException {
    return new JndiDataSourceLookup().getDataSource("jdbc/arts2DS");
  }

  @Bean
  public DataSource identityDataSource()
          throws NamingException {
    return new JndiDataSourceLookup().getDataSource("jdbc/identityDS");
  }

  @Bean
  public DataSource quartzDataSource()
          throws NamingException {
    return new JndiDataSourceLookup().getDataSource("jdbc/quartzDS");
  }
}
