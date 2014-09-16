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
            .withTitleId("test-title")
            .withName("Something Interesting Happens!")
            .withCategory("Test")
            .withVenueId("somint")
            .withAddress("Somewhere Interesting, YO8 8FW")
            .withMajor(1)
            .withNextPerformance("20/10/2014")
            .withLastPerformance("20/12/2014")
            .withLevel2("This is something about the test title. Probably should've used Lorem Ipsum.")
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

  public static Title testTitleStub() {
    return TitleBuilder
            .aTitle()
            .withTitleId("test-title")
            .withName("Something Interesting Happens!")
            .withCategory("Test")
            .withVenueId("somint")
            .withAddress("Somewhere Interesting, YO8 8FW")
            .withNextPerformance("20/10/2014")
            .withLastPerformance("20/12/2014")
            .build();
  }
}
