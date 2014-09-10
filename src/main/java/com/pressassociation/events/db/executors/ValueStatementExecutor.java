package com.pressassociation.events.db.executors;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * ****************************************************************************************
 *
 * @author <a href="ralph.hodgson@pressassociation.com">Ralph Hodgson</a>
 * @since 03/09/2014 13:37
 * <p/>
 * ****************************************************************************************
 */
public class ValueStatementExecutor implements StatementExecutor<Integer> {
  private String sql;

  public ValueStatementExecutor(String sql) {
    this.sql = sql;
  }

  @Override
  public Integer execute(Statement statement)
          throws SQLException {
    statement.execute(sql);
    statement.getResultSet().first();
    return statement.getResultSet().getInt(1);
  }
}
