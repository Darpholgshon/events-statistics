package com.pressassociation.events.db.model;

import com.google.common.base.Objects;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * ****************************************************************************************
 *
 * @author <a href="ralph.hodgson@pressassociation.com">Ralph Hodgson</a>
 * @since 08/09/2014 12:20
 * <p/>
 * ****************************************************************************************
 */
@XmlRootElement(name="titles")
@XmlAccessorType(XmlAccessType.FIELD)
public class Title {

  @XmlAttribute(required=true)
  private String titleId;

  @XmlElement
  private String name;

  @XmlElement
  private String category;

  @XmlElement
  private String level2;

  @XmlElement
  private String venueId;

  @XmlElement
  private String address;

  @XmlElement
  private Integer major;

  @XmlElement
  private String nextPerformance;

  @XmlElement
  private String lastPerformance;

  @XmlElement
  private List<Title> titles;

  public String getTitleId() {
    return titleId;
  }

  public void setTitleId(String titleId) {
    this.titleId = titleId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getVenueId() {
    return venueId;
  }

  public void setVenueId(String venueId) {
    this.venueId = venueId;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Integer getMajor() {
    return major;
  }

  public void setMajor(Integer major) {
    this.major = major;
  }

  public String getNextPerformance() {
    return nextPerformance;
  }

  public void setNextPerformance(String nextPerformance) {
    this.nextPerformance = nextPerformance;
  }

  public String getLastPerformance() {
    return lastPerformance;
  }

  public void setLastPerformance(String lastPerformance) {
    this.lastPerformance = lastPerformance;
  }

  public String getLevel2() {
    return level2;
  }

  public void setLevel2(String level2) {
    this.level2 = level2;
  }

  public List<Title> getTitles() {
    return titles;
  }

  public void setTitles(List<Title> titles) {
    this.titles = titles;
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
                  .add("titleId", titleId)
                  .add("name", name)
                  .add("category", category)
                  .add("venueId", venueId)
                  .add("address", address)
                  .add("major", major)
                  .add("nextPerformance", nextPerformance)
                  .add("lastPerformance", lastPerformance)
                  .add("level2", level2)
                  .omitNullValues()
                  .toString();
  }
}
