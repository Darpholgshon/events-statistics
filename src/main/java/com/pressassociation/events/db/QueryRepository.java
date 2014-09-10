package com.pressassociation.events.db;

import com.pressassociation.events.db.executors.ValueStatementExecutor;
import com.pressassociation.events.db.executors.TitleStatementExecutor;
import com.pressassociation.events.db.executors.VenueStatementExecutor;
import com.pressassociation.events.db.model.Title;
import com.pressassociation.events.db.model.Venue;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * ****************************************************************************************
 *
 * @author <a href="ralph.hodgson@pressassociation.com">Ralph Hodgson</a>
 * @since 03/09/2014 11:03
 * <p/>
 * ****************************************************************************************
 */
@Repository
public class QueryRepository extends AbstractRepository{

  public static final String LIVE_EVENTS = "select count(*) from"
          + " (select distinct titleid, festivalid from event e "
          + " join eventtime et on e.autoid = et.eventid"
          + " where CONCAT_WS(' ', et.date, COALESCE(et.time, CAST('00:00:00' AS TIME) ) ) >= now()) as temp;";


  public static final String LIVE_OCCURRENCES = "select count(*) from eventtime et"
          + " where CONCAT_WS(' ', et.date, COALESCE(et.time, CAST('00:00:00' AS TIME) ) ) >= now();";

  public static final String INCOMPLETE_TITLES = "select count(*) from"
          + "  (select distinct e.titleid from event e"
          + "  join eventtime et on e.autoid = et.eventid"
          + "  left join arts2.clientdesc d1 ON e.titleid = d1.titleid AND d1.clientid = 3"
          + "  where CONCAT_WS(' ', et.date, COALESCE(et.time, CAST('00:00:00' AS TIME) ) ) >= now()"
          + "  and d1.description is null) as temp;";

  private static final String UNMAPPED_VENUES = "select count(*) from identifier_mapping"
          + " where mapping_type_id = 11 and target_entity_id is null;";

  @Cacheable(value = "queryCache", key="'count-live-events'")
  public int countLiveEvents()
          throws SQLException {
    return executeStatment(eventsDataSource, new ValueStatementExecutor(LIVE_EVENTS));
  }

  @Cacheable(value = "queryCache", key="'count-live-occurrences'")
  public int countLiveOccurrences()
          throws SQLException {
    return executeStatment(eventsDataSource, new ValueStatementExecutor(LIVE_OCCURRENCES) );
  }

  @Cacheable(value = "queryCache", key="'count-incomplete-titles'")
  public int countIncompleteTitles()
          throws SQLException {
    return executeStatment(eventsDataSource,new ValueStatementExecutor(INCOMPLETE_TITLES) );
  }

  @Cacheable(value = "queryCache", key="'count-unmapped-venues'")
  public int countUnmappedVenues()
          throws SQLException {
    return executeStatment(identityDataSource,new ValueStatementExecutor(UNMAPPED_VENUES) );
  }

  @Cacheable(value = "queryCache", key="'list-unmapped-venues'")
  public List<Venue> getUnmappedVenues()
          throws SQLException {
    return executeStatment(identityDataSource, new VenueStatementExecutor());
  }

  @Cacheable(value = "queryCache", key="'list-incomplete-titles'")
  public List<Title> getIncompleteTitles()
          throws SQLException {
    return executeStatment(eventsDataSource, new TitleStatementExecutor());
  }
}
