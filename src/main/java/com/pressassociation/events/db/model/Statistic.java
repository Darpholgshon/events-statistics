package com.pressassociation.events.db.model;

import com.google.common.base.Objects;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * ****************************************************************************************
 *
 * @author <a href="ralph.hodgson@pressassociation.com">Ralph Hodgson</a>
 * @since 03/09/2014 12:47
 * <p/>
 * ****************************************************************************************
 */
@XmlRootElement(name="statistics")
@XmlAccessorType(XmlAccessType.FIELD)
public class Statistic {

  @XmlAttribute
  private String key;

  @XmlAttribute
  private Integer value;

  @XmlElement(name="statistic")
  List<Statistic> statisticList;

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public Integer getValue() {
    return value;
  }

  public void setValue(Integer value) {
    this.value = value;
  }

  public List<Statistic> getStatisticList() {
    return statisticList;
  }

  public void setStatisticList(List<Statistic> statisticList) {
    this.statisticList = statisticList;
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
                  .add("key", key)
                  .add("value", value)
                  //.add("statisticList", statisticList)
                  .omitNullValues()
                  .toString();
  }
}
