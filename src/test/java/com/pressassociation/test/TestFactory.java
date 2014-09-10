package com.pressassociation.test;

import com.google.common.collect.Lists;
import com.pressassociation.events.db.model.*;

/**
 * ****************************************************************************************
 *
 * @author <a href="ralph.hodgson@pressassociation.com">Ralph Hodgson</a>
 * @since 09/09/2014 10:12
 * <p/>
 * ****************************************************************************************
 */
public class TestFactory {

  private static Statistic testStatistic() {
    return StatisticBuilder
            .aStatistic()
            .withKey("test-key")
            .withValue(100)
            .build();
  }
  public static Title testTitle() {
    return TitleBuilder
            .aTitle()
            .withId("test-title")
            .withName("Something Interesting Happens!")
            .withCategory("Test")
            .build();
  }
  public static Venue testVenue() {
    return VenueBuilder
            .aVenue()
            .withId("test-venue")
            .withAddress("24 Blackthorn Close, Selby, YO8 8FW")
            .build();
  }
  public static Statistic testStatisticWrapper() {
    return StatisticBuilder
            .aStatistic()
            .withStatisticList(testStatistic())
            .build();
  }

  public static Title testTitleWrapper() {
    return TitleBuilder
            .aTitle()
            .withTitleList(Lists.newArrayList(testTitle()))
            .build();
  }

  public static Venue testVenueWrapper() {
    return VenueBuilder
            .aVenue()
            .withVenueList(Lists.newArrayList(testVenue()))
            .build();
  }
}
