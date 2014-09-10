package com.pressassociation.events.db.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * ****************************************************************************************
 *
 * @author <a href="ralph.hodgson@pressassociation.com">Ralph Hodgson</a>
 * @since 08/09/2014 11:18
 * <p/>
 * ****************************************************************************************
 */
public class VenueBuilder {
  protected static final Logger LOG = LoggerFactory.getLogger(VenueBuilder.class);

  private String id;
  private String address;

  private List<Venue> venueList;

  private VenueBuilder() {
  }

  public static VenueBuilder aVenue() {
    return new VenueBuilder();
  }

  public VenueBuilder withId(String id) {
    this.id = id;
    return this;
  }

  public VenueBuilder withAddress(String address) {
    this.address = address;
    return this;
  }

  public VenueBuilder withVenueList(List<Venue> venues) {
    this.venueList = venues;
    return this;
  }


  public void buildCollection(java.util.Collection<Venue> collection) {
    collection.add(build());
  }

  public Venue build() {
    Venue venue = new Venue();
    venue.setId(id);
    venue.setAddress(address);
    venue.setVenues(venueList);
    LOG.debug("Built => {}", venue);
    return venue;
  }
}
