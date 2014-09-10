package com.pressassociation.events.db.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * ****************************************************************************************
 *
 * @author <a href="ralph.hodgson@pressassociation.com">Ralph Hodgson</a>
 * @since 09/09/2014 14:11
 * <p/>
 * ****************************************************************************************
 */
public class TitleBuilder {
  protected static final Logger LOG = LoggerFactory.getLogger(TitleBuilder.class);

  private String id;
  private String name;
  private String category;
  private List<Title> titles;

  private TitleBuilder() {
  }

  public static TitleBuilder aTitle() {
    return new TitleBuilder();
  }

  public TitleBuilder withId(String id) {
    this.id = id;
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

  public TitleBuilder withTitleList(List<Title> titles) {
    this.titles = titles;
    return this;
  }

  public void buildCollection(java.util.Collection<Title> collection) {
    collection.add(build());
  }

  public Title build() {
    Title title = new Title();
    title.setId(id);
    title.setName(name);
    title.setCategory(category);
    title.setTitles(titles);

    LOG.debug("Built: {}", title);
    return title;
  }
}
