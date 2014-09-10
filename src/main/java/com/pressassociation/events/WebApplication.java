package com.pressassociation.events;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

/**
 * ****************************************************************************************
 *
 * @author <a href="ralph.hodgson@pressassociation.com">Ralph Hodgson</a>
 * @since 03/09/2014 10:10
 * <p/>
 * ****************************************************************************************
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration(exclude = {
        // Exclude these auto configurations, because they only work when there is 1 datasource.
        HibernateJpaAutoConfiguration.class,
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class
})
public class WebApplication {
  public static void main(String[] args)
          throws SQLException {
    SpringApplication.run(WebApplication.class, args);
  }
}
