package com.pressassociation.events.db.model;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * ****************************************************************************************
 *
 * @author <a href="ralph.hodgson@pressassociation.com">Ralph Hodgson</a>
 * @since 08/09/2014 11:51
 * <p/>
 * ****************************************************************************************
 */
public class StatisticBuilder {
  List<Statistic> statisticList;
  private String key;
  private Integer value;

  private StatisticBuilder() {
  }

  public static StatisticBuilder aStatistic() {
    return new StatisticBuilder();
  }

  public StatisticBuilder withKey(String key) {
    this.key = key;
    return this;
  }

  public StatisticBuilder withValue(Integer value) {
    this.value = value;
    return this;
  }

  public StatisticBuilder withStatisticList(Statistic... statistics) {
    this.statisticList = statistics == null ? null : Lists.newArrayList(statistics);
    return this;
  }

  public void buildCollection(java.util.Collection<Statistic> collection) {
    collection.add(build());
  }

  public Statistic build() {
    Statistic statistic = new Statistic();
    statistic.setKey(key);
    statistic.setValue(value);
    statistic.setStatisticList(statisticList);
    return statistic;
  }
}
