package com.pressassociation.events.controller;

import com.pressassociation.events.db.model.Statistic;
import com.pressassociation.events.db.model.Title;
import com.pressassociation.events.db.model.Venue;
import com.pressassociation.events.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;

/**
 * ****************************************************************************************
 *
 * @author <a href="ralph.hodgson@pressassociation.com">Ralph Hodgson</a>
 * @since 03/09/2014 10:01
 * <p/>
 * ****************************************************************************************
 */
@Controller
@RequestMapping("/query")
public class QueryController {
  protected static final Logger LOG = LoggerFactory.getLogger(QueryController.class);

  @Autowired
  QueryService service;

  @PostConstruct
  public void postConstruct(){
    LOG.debug("Query Controller created.");
  }

  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
  @ResponseBody
  public Statistic get() throws Exception{
    return service.queryCounts();
  }

  @RequestMapping(value = "/unmapped-venues",method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
  @ResponseBody
  public Venue getUnmappedVenues() throws Exception{
    return service.getUnmappedVenues();
  }

  @RequestMapping(value = "/incomplete-titles",method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
  @ResponseBody
  public Title getIncompleteTitles() throws Exception{
    return service.getIncompleteTitles();
  }
}
