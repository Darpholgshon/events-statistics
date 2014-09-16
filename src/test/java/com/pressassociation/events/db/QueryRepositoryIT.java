package com.pressassociation.events.db;

import com.pressassociation.events.config.DataSourceConfiguration;
import com.pressassociation.events.db.model.Title;
import com.pressassociation.events.db.model.Venue;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * ****************************************************************************************
 *
 * @author <a href="ralph.hodgson@pressassociation.com">Ralph Hodgson</a>
 * @since 03/09/2014 10:42
 * <p/>
 * ****************************************************************************************
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourceConfiguration.class} )
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
@ActiveProfiles("integration")
public class QueryRepositoryIT {
  protected static final Logger LOG = LoggerFactory.getLogger(QueryRepositoryIT.class);

  @Autowired
  QueryRepository repository;

  @Test
  public void testEventCount()
          throws SQLException {

    int count = repository.countLiveEvents();
    assertThat(count, not(is(0)));

    LOG.debug("Currently have {} potential Listora live events in the arts2 database.", count);
  }

  @Test
  public void testOccurrenceCount()
          throws SQLException {

    int count = repository.countLiveOccurrences();
    assertThat(count, not(is(0)));

    LOG.debug("Currently have {} potential Listora live occurrences in the arts2 database.", count);
  }

  @Test
  public void testIncompleteTitlesCount()
          throws SQLException {

    int count = repository.countIncompleteTitles();
    assertThat(count, not(is(0)));

    LOG.debug("Currently have {} titles without L1 descriptions in the arts2 database.", count);
  }

  @Test
  public void testIncompleteTitles()
          throws SQLException {

    List<Title> titles = repository.getIncompleteTitles();
    assertThat(titles.isEmpty(), is(false));

    LOG.debug("Currently have {} titles without L1 descriptions in the arts2 database.", titles.size());

    LOG.debug("Example: {} ", titles.get(0));

  }


  @Test
  public void testUnmappedVenuesCount()
          throws SQLException {

    int count = repository.countUnmappedVenues();
    assertThat(count, not(is(0)));

    LOG.debug("Currently have {} Unmapped venues in the identity database.", count);
  }

  @Test
  public void testUnmappedVenues()
          throws SQLException {

    List<Venue> venues = repository.getUnmappedVenues();
    assertThat(venues.size(), not(is(0)));

    LOG.debug("Currently have {} Unmapped venues in the identity database.", venues.size());
  }

}
