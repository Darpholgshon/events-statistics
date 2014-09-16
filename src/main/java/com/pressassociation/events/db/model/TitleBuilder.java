package com.pressassociation.events.db.model;

import java.util.List;

/**
 * ****************************************************************************************
 *
 * @author <a href="ralph.hodgson@pressassociation.com">Ralph Hodgson</a>
 * @since 12/09/2014 15:19
 * <p/>
 * ****************************************************************************************
 */
public class TitleBuilder {
  private String titleId;
  private String name;
  private String category;
  private String level2;
  private String venueId;
  private String address;
  private Integer major;
  private String nextPerformance;
  private String lastPerformance;
  private List<Title> titles;

  private TitleBuilder() {
  }

  public static TitleBuilder aTitle() {
    return new TitleBuilder();
  }

  public TitleBuilder withTitleId(String titleId) {
    this.titleId = titleId;
    return this;
  }

  public TitleBuilder withName(String name) {
    this.name = name;
    return this;
  }

  public TitleBuilder withCategory(String category) {
    this.category = category;
    return this;
  }

  public TitleBuilder withLevel2(String level2) {
    this.level2 = level2;
    return this;
  }

  public TitleBuilder withVenueId(String venueId) {
    this.venueId = venueId;
    return this;
  }

  public TitleBuilder withAddress(String address) {
    this.address = address;
    return this;
  }

  public TitleBuilder withMajor(Integer major) {
    this.major = major;
    return this;
  }

  public TitleBuilder withNextPerformance(String nextPerformance) {
    this.nextPerformance = nextPerformance;
    return this;
  }

  public TitleBuilder withLastPerformance(String lastPerformance) {
    this.lastPerformance = lastPerformance;
    return this;
  }

  public TitleBuilder withTitleList(List<Title> titles) {
    this.titles = titles;
    return this;
  }

  public void buildCollection(java.util.Collection<Title> collection) {
    collection.add(build());
  }

  public Title build() {
    Title title = new Title();
    title.setTitleId(titleId);
    title.setName(name);
    title.setCategory(category);
    title.setLevel2(level2);
    title.setVenueId(venueId);
    title.setAddress(address);
    title.setMajor(major);
    title.setNextPerformance(nextPerformance);
    title.setLastPerformance(lastPerformance);
    title.setTitles(titles);
    return title;
  }
}
