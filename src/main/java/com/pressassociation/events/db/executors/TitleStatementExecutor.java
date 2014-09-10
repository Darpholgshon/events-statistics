package com.pressassociation.events.db.executors;

import com.google.common.collect.Lists;
import com.pressassociation.events.db.model.Title;
import com.pressassociation.events.db.model.TitleBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * ****************************************************************************************
 *
 * @author <a href="ralph.hodgson@pressassociation.com">Ralph Hodgson</a>
 * @since 08/09/2014 13:42
 * <p/>
 * ****************************************************************************************
 */
public class TitleStatementExecutor
        implements StatementExecutor<java.util.List<com.pressassociation.events.db.model.Title>> {

  public static final String SQL = "SELECT DISTINCT t.titleid, t.name,"
          + "      CASE"
          + "         WHEN c3.name is not null THEN c3.name"
          + "         WHEN c2.name is not null THEN c2.name"
          + "         ELSE c1.name"
          + "       END as category"
          + "      FROM title t"
          + "      JOIN titlecategory tc ON tc.titleid = t.titleid AND tc.sequenceno = 1"
          + "      JOIN categorynode c1 ON tc.categorynodeid = c1.categorynodeid"
          + " LEFT JOIN categorynode c2 ON c1.parentcategorynodeid = c2.categorynodeid"
          + " LEFT JOIN categorynode c3 ON c2.parentcategorynodeid = c3.categorynodeid"
          + "      JOIN event e ON e.titleid = t.titleid"
          + "      JOIN eventtime et ON e.autoid = et.eventid"
          + " LEFT JOIN arts2.clientdesc d1 ON e.titleid = d1.titleid AND d1.clientid = 3"
          + "     WHERE CONCAT_WS(' ', et.date, COALESCE(et.time, CAST('00:00:00' AS TIME) ) ) >= now()"
          + "       AND d1.description IS NULL ORDER BY 3, 1";

  @Override
  public List<Title> execute(Statement statement)
          throws SQLException {
    statement.execute(SQL);

    List<Title> titles = Lists.newArrayList();

    ResultSet rs = statement.getResultSet();

    if (rs.first()) {  // If there are results, iterate round them till we get to the last one.
      do {
        TitleBuilder
                .aTitle()
                .withId(rs.getString(1))
                .withName(rs.getString(2))
                .withCategory(rs.getString(3))
                .buildCollection(titles);
        rs.next();
      } while (!rs.isLast());
    }
    return titles;
  }

}
