package com.pressassociation.events.db.executors;

import com.google.common.collect.Lists;
import com.pressassociation.events.db.model.Venue;
import com.pressassociation.events.db.model.VenueBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * ****************************************************************************************
 *
 * @author <a href="ralph.hodgson@pressassociation.com">Ralph Hodgson</a>
 * @since 08/09/2014 11:11
 * <p/>
 * ****************************************************************************************
 */
public class VenueStatementExecutor implements StatementExecutor<List<Venue>> {
  protected static final Logger LOG = LoggerFactory.getLogger(VenueStatementExecutor.class);

  public static final String SQL = "SELECT source_entity_id, description FROM identifier_mapping "
                                 + " WHERE mapping_type_id = 11 AND target_entity_id IS NULL order by 1;";

  @Override
  public List<Venue> execute(Statement statement)
          throws SQLException {
    statement.execute(SQL);

    List<Venue> venues = Lists.newArrayList();

    ResultSet rs = statement.getResultSet();

    if (rs.first()) {  // If there are results, iterate round them till we get to the last one.
      do {
        VenueBuilder
                .aVenue()
                .withId(rs.getString(1))
                .withAddress(rs.getString(2))
                .buildCollection(venues);
        rs.next();
      } while (!rs.isLast());
    }
    return venues;
  }

}
