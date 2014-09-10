package com.pressassociation.events.service;

import com.pressassociation.events.db.model.Statistic;
import com.pressassociation.events.db.model.Title;
import com.pressassociation.events.db.model.Venue;

import java.sql.SQLException;

/**
 * ****************************************************************************************
 *
 * @author <a href="ralph.hodgson@pressassociation.com">Ralph Hodgson</a>
 * @since 09/09/2014 09:30
 * <p/>
 * ****************************************************************************************
 */
public interface QueryService {
  Statistic queryCounts()
          throws SQLException;

  Venue getUnmappedVenues()
                  throws SQLException;

  Title getIncompleteTitles()
                          throws SQLException;
}
