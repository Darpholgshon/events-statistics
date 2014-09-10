package com.pressassociation.events.route;

import org.apache.camel.spring.SpringRouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * ****************************************************************************************
 *
 * @author <a href="ralph.hodgson@pressassociation.com">Ralph Hodgson</a>
 * @since 03/09/2014 15:53
 * <p/>
 * ****************************************************************************************
 */
@Component
public class ReportScheduleRoute extends SpringRouteBuilder {
  protected static final Logger LOG = LoggerFactory.getLogger(ReportScheduleRoute.class);

  protected static final String SCHED_LOG_MSG = "id=${id}";

  @Override
  public void configure()
          throws Exception {
    LOG.debug("Configuring {} Route", getClass().getSimpleName());

    // Setup Error Handler
    errorHandler(loggingErrorHandler(this.getClass().getSimpleName()));

    // Exception Handling
    onException(Exception.class)
            .maximumRedeliveries("{{retry.count}}")
            .redeliveryDelay("{{retry.delay}}")
            .handled(true)
            .setBody(simple("body=${bodyAs(String)}, exception=${exception}, trace=${exception.stacktrace}"))
            .to("{{error.log}}");

    // Scheduled Routing
    from("{{report.scheduler.route}}")
            .routeId(this.getClass().getSimpleName())
            .log("Starting Sync: " + SCHED_LOG_MSG)
            .to("bean:reportService?method=createReport(${header.scheduledFireTime})")
            .log("Completed Sync: " + SCHED_LOG_MSG);

  }
}
