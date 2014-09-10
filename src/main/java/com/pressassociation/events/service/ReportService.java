package com.pressassociation.events.service;

import java.util.Date;

/**
 * ****************************************************************************************
 *
 * @author <a href="ralph.hodgson@pressassociation.com">Ralph Hodgson</a>
 * @since 09/09/2014 09:28
 * <p/>
 * ****************************************************************************************
 */
public interface ReportService {
  void createReport(Date firedAt)
          throws Exception;
}
