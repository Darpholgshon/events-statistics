package com.pressassociation.events.service.impl;

import com.pressassociation.events.db.QueryRepository;
import com.pressassociation.events.db.model.*;
import com.pressassociation.events.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.SQLException;

/**
 * ****************************************************************************************
 *
 * @author <a href="ralph.hodgson@pressassociation.com">Ralph Hodgson</a>
 * @since 03/09/2014 12:42
 * <p/>
 * ****************************************************************************************
 */
@Service("queryService")
public class QueryServiceImpl implements QueryService {
  protected static final Logger LOG = LoggerFactory.getLogger(QueryServiceImpl.class);

  @Autowired
  QueryRepository repository;

  @PostConstruct
  public void postConstruct(){
    LOG.debug("Query Service Created");
  }

  @Override
  public Statistic queryCounts()
          throws SQLException {
    return StatisticBuilder
            .aStatistic()
            .withStatisticList(
                    StatisticBuilder
                            .aStatistic()
                            .withKey("live-events").withValue(repository.countLiveEvents())
                            .build(),
                    StatisticBuilder
                            .aStatistic()
                            .withKey("live-occurrences").withValue(repository.countLiveOccurrences())
                            .build(),
                    StatisticBuilder
                            .aStatistic()
                            .withKey("incomplete-titles").withValue(repository.countIncompleteTitles())
                            .build(),
                    StatisticBuilder
                            .aStatistic()
                            .withKey("unmapped-venues").withValue(repository.countUnmappedVenues())
                            .build()
            ).build();
  }

  @Override
  public Venue getUnmappedVenues()
          throws SQLException {
    return VenueBuilder
            .aVenue()
            .withVenueList(repository.getUnmappedVenues())
            .build();
  }

  @Override
  public Title getIncompleteTitles()
          throws SQLException {
    return TitleBuilder
            .aTitle()
            .withTitleList(repository.getIncompleteTitles())
            .build();
  }
}
