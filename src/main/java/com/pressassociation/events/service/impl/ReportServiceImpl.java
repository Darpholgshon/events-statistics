package com.pressassociation.events.service.impl;

import com.google.common.collect.Maps;
import com.pressassociation.events.db.model.Statistic;
import com.pressassociation.events.service.QueryService;
import com.pressassociation.events.service.ReportService;
import com.pressassociation.events.util.FileType;
import com.pressassociation.events.util.FreemarkerUtil;
import com.pressassociation.events.util.MailDaemon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.Date;
import java.util.Map;

/**
 * ****************************************************************************************
 *
 * @author <a href="ralph.hodgson@pressassociation.com">Ralph Hodgson</a>
 * @since 03/09/2014 15:24
 * <p/>
 * ****************************************************************************************
 */
@Service("reportService")
public class ReportServiceImpl implements ReportService {
  protected static final Logger LOG = LoggerFactory.getLogger(ReportServiceImpl.class);

  @Autowired
  QueryService queryService;

  @Autowired
  MailDaemon mailDaemon;

  @PostConstruct
  public void postConstruct(){
    LOG.debug("Report Service Created");
  }

  @Override
  public void createReport(Date firedAt)
          throws Exception {
    LOG.info("Time is {}", firedAt );
    Statistic stats = queryService.queryCounts();

    Map<String, Object> data = Maps.newHashMap();
    data.put("venues", queryService.getUnmappedVenues().getVenues());
    data.put("titles", queryService.getIncompleteTitles().getTitles());

    File f1 = FreemarkerUtil.toFile(data, "venues", FileType.CSV);
    File f2 = FreemarkerUtil.toFile(data, "titles", FileType.CSV);

    LOG.info("Reports created. Sending mail.");
    mailDaemon.sendMessage("Events Reporting", FreemarkerUtil.statisticsHTML(stats.getStatisticList(), firedAt), f1, f2);
  }
}
