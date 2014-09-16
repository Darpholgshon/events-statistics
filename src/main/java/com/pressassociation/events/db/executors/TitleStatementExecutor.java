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

  public static final String SQL = "SELECT"
          + " t.titleid AS titleId, "
          + " t.name AS name,"
          + " ROOT_NAME(tc.categorynodeid) AS category,"
          + " d2.description AS level2,"
          + " v.venuid AS venueId,"
          + " CONCAT_WS(', ', CONCAT_WS(', ', v.name, v.town), v.postcode) AS address,"
          + " v.major AS major,"
          + " min(et.date) AS nextPerformance, "
          + " max(et.date) AS lastPerformance"
          + "                FROM title t"
          + "                JOIN event e ON e.titleid = t.titleid"
          + "                JOIN eventtime et ON e.autoid = et.eventid AND et.date >= current_date()"
          + "                JOIN venue v ON v.venuid = e.venuid"
          + "                JOIN titlecategory tc ON tc.titleid = t.titleid AND tc.sequenceno = 1"
          + "           LEFT JOIN clientdesc d1 ON t.titleid = d1.titleid AND d1.clientid = 3"
          + "           LEFT JOIN clientdesc d2 ON t.titleid = d2.titleid AND d2.clientid = 0"
          + "               WHERE d1.description IS NULL AND e.enddate > current_date()"
          + "            GROUP BY t.titleid, v.venuid"
          + "            ORDER BY category, lastPerformance desc;";

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
                .withTitleId(rs.getString(1))
                .withName(rs.getString(2))
                .withCategory(rs.getString(3))
                .withLevel2(rs.getString(4))
                .withVenueId(rs.getString(5))
                .withAddress(rs.getString(6))
                .withMajor(rs.getInt(7))
                .withNextPerformance(rs.getString(8))
                .withLastPerformance(rs.getString(9))
                .buildCollection(titles);
        rs.next();
      } while (!rs.isLast());
    }
    return titles;
  }

}
