package com.pressassociation.events.db;

import com.pressassociation.events.config.DataSourceConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * ****************************************************************************************
 *
 * @author <a href="ralph.hodgson@pressassociation.com">Ralph Hodgson</a>
 * @since 03/09/2014 14:32
 * <p/>
 * ****************************************************************************************
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourceConfiguration.class} )
@ActiveProfiles("integration")
public class ConnectionIT {
  protected static final Logger LOG = LoggerFactory.getLogger(ConnectionIT.class);

  @Autowired
  @Qualifier("eventsDataSource")
  DataSource dataSource;

  @Test
  public void connectionTest()
          throws SQLException {
    Connection c = dataSource.getConnection();
    Statement statement = c.createStatement();
    statement.execute("SELECT titleid FROM title WHERE titleid LIKE 'bat%' LIMIT  10");
    statement.getResultSet().first();

    do {
      String titleId = statement.getResultSet().getString(1);
      assertThat(titleId, startsWith("bat"));

      LOG.info("Result of select title id was => " + titleId);
      statement.getResultSet().next();
    } while (!statement.getResultSet().isLast());

    assertThat(statement.getResultSet().getRow(), is(10));
    statement.close();
  }

}
