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
  private String id;

  @XmlElement
  private String name;

  @XmlElement
  private String category;

  @XmlElement
  private List<Title> titles;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
                  .add("id", id)
                  .add("name", name)
                  .add("category", category)
                  //.add("titles", titles)
                  .omitNullValues()
                  .toString();
  }
}
