package com.pressassociation.events.db.model;

import com.google.common.base.Objects;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * ****************************************************************************************
 *
 * @author <a href="ralph.hodgson@pressassociation.com">Ralph Hodgson</a>
 * @since 08/09/2014 11:07
 * <p/>
 * ****************************************************************************************
 */
@XmlRootElement(name="venues")
@XmlAccessorType(XmlAccessType.FIELD)
public class Venue {

  @XmlAttribute(required=true)
  private String id;

  @XmlElement
  private String address;

  @XmlElement
  private List<Venue> venues;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public List<Venue> getVenues() {
    return venues;
  }

  public void setVenues(List<Venue> venues) {
    this.venues = venues;
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
                  .add("id", id)
                  .add("address", address)
                  //.add("venues", venues)
                  .omitNullValues()
                  .toString();
  }
}
