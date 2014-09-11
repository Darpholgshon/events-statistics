package com.pressassociation.events.util;

import com.google.common.collect.Maps;
import com.pressassociation.events.db.model.Statistic;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ****************************************************************************************
 *
 * @author <a href="ralph.hodgson@pressassociation.com">Ralph Hodgson</a>
 * @since 05/09/2014 17:31
 * <p/>
 * ****************************************************************************************
 */
public class FreemarkerUtil {

  public static String statisticsHTML(List<Statistic> statistics, Date firedAt)
          throws IOException, TemplateException {
    Map<String, Object> data = Maps.newHashMap();
    data.put("statistics", statistics);
    data.put("today", firedAt.toString());

    return toHTML(data, "statistics");
  }

  public static String toHTML(Map<String, Object> dataMap, String templateName)
          throws IOException, TemplateException {
    StringWriter sw = new StringWriter();
    processTemplate(dataMap, sw, templateName);
    return sw.toString();
  }

  public static File toFile(Map<String, Object> dataMap, String templateName, FileType extension)
          throws IOException, TemplateException {
    File temp = File.createTempFile(templateName, extension.suffix());
    processTemplate(dataMap, new FileWriter(temp), templateName);
    return temp;
  }

  private static void processTemplate(Map<String, Object> dataMap, Writer writer, String templateName)
          throws IOException, TemplateException {
    Configuration cfg = new freemarker.template.Configuration();
    cfg.setClassForTemplateLoading(FreemarkerUtil.class, "templates");

    cfg.getTemplate(FileType.FTL.filename(templateName)).process(dataMap, writer);
  }
}
