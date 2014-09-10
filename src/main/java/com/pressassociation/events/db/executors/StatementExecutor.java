package com.pressassociation.events.db.executors;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * ****************************************************************************************
 *
 * @author <a href="ralph.hodgson@pressassociation.com">Ralph Hodgson</a>
 * @since 03/09/2014 13:39
 * <p/>
 * ****************************************************************************************
 */
public interface StatementExecutor<T> {
  public T execute(Statement statement)
          throws SQLException;
}
