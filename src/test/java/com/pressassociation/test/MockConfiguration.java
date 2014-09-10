package com.pressassociation.test;

import com.pressassociation.events.service.QueryService;
import com.pressassociation.events.service.ReportService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

/**
 * ****************************************************************************************
 *
 * @author <a href="ralph.hodgson@pressassociation.com">Ralph Hodgson</a>
 * @since 09/09/2014 09:22
 * <p/>
 * ****************************************************************************************
 */
@Configuration
public class MockConfiguration {
  @Bean
  public ReportService reportService() {
    return mock(ReportService.class);
  }

  @Bean
  public QueryService queryService() {
    return mock(QueryService.class);
  }
}
