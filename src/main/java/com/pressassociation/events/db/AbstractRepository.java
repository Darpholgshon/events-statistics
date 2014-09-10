package com.pressassociation.events.db;

import com.pressassociation.events.db.executors.StatementExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ****************************************************************************************
 *
 * @author <a href="ralph.hodgson@pressassociation.com">Ralph Hodgson</a>
 * @since 03/09/2014 11:35
 * <p/>
 * ****************************************************************************************
 */
public abstract class AbstractRepository {

  @Autowired
  @Qualifier(value="eventsDataSource")
  DataSource eventsDataSource;

  @Autowired
  @Qualifier(value="identityDataSource")
  DataSource identityDataSource;

  protected <T> T executeStatment(DataSource dataSource, StatementExecutor<T> callback)
          throws SQLException {
    Connection connection = null;
    Statement statement = null;

    try {
      connection = dataSource.getConnection();
      statement = connection.createStatement();

      return callback.execute(statement);
    }
    finally{
      if (statement != null) {
        statement.close();
      }
      if (connection != null) {
        connection.close();
      }
    }
  }
}


