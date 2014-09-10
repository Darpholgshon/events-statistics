package com.pressassociation.events.config;

import com.google.common.collect.Lists;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.spring.SpringCamelContext;
import org.apache.camel.spring.javaconfig.CamelConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * ****************************************************************************************
 *
 * @author <a href="ralph.hodgson@pressassociation.com">Ralph Hodgson</a>
 * @since 03/09/2014 15:45
 * <p/>
 * ****************************************************************************************
 */
@Configuration
@ComponentScan("com.pressassociation.events.route")
public class MyCamelConfiguration extends CamelConfiguration {

  protected static final Logger LOG = LoggerFactory.getLogger(MyCamelConfiguration.class);

  @Autowired
  ApplicationContext context;

  @Override
  protected void setupCamelContext(CamelContext camelContext)
          throws Exception {
    LOG.info("Camel Context is being initialized!");

    ((SpringCamelContext) camelContext).setManagementName("#name#");

    // Add in properties.
    PropertiesComponent pc = new PropertiesComponent();
    pc.setLocation("classpath:application.properties");
    camelContext.addComponent("properties", pc);
  }

  @Override
  public List<RouteBuilder> routes() {
    LOG.debug("Configuring {} Routes...");

    List<RouteBuilder> builders = Lists.newArrayList();
    builders.addAll(context.getBeansOfType(RouteBuilder.class).values());
    return builders;
  }
}
