package com.pressassociation.events.service;

import com.pressassociation.events.service.impl.ReportServiceImpl;
import com.pressassociation.events.util.MailDaemon;
import com.pressassociation.test.TestFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.util.Date;

import static org.mockito.Mockito.*;

/**
 * ****************************************************************************************
 *
 * @author <a href="ralph.hodgson@pressassociation.com">Ralph Hodgson</a>
 * @since 09/09/2014 10:01
 * <p/>
 * ****************************************************************************************
 */
@RunWith(MockitoJUnitRunner.class)
public class ReportServiceTest {

  @Mock
  private QueryService queryService;

  @Mock
  private MailDaemon mailDaemon;

  @InjectMocks
  private ReportServiceImpl reportService;

  @Test
  public void testReport() throws Exception {
    when(queryService.getIncompleteTitles())
            .thenReturn(TestFactory.testTitleWrapper());
    when(queryService.getUnmappedVenues())
            .thenReturn(TestFactory.testVenueWrapper());
    when(queryService.queryCounts())
            .thenReturn(TestFactory.testStatisticWrapper());

    reportService.createReport(new Date());

    verify(queryService, times(1)).getIncompleteTitles();
    verify(queryService, times(1)).getUnmappedVenues();
    verify(queryService, times(1)).queryCounts();

    verify(mailDaemon, times(1)).sendMessage(anyString(), anyString(), any(File.class), any(File.class));
  }
}
