package com.pressassociation.events.util;

import com.google.common.collect.Maps;
import com.pressassociation.events.db.model.Statistic;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
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
    Configuration cfg = new freemarker.template.Configuration();
    cfg.setDirectoryForTemplateLoading(file("templates"));
    Template template = cfg.getTemplate(FileType.FTL.filename(templateName));

    StringWriter sw = new StringWriter();
    template.process(dataMap, sw);

    return sw.toString();
  }

  public static File toFile(Map<String, Object> dataMap, String templateName, FileType extension)
          throws IOException, TemplateException {

    File temp = File.createTempFile(templateName, extension.suffix());
    FileWriter fw = new FileWriter(temp);

    Configuration cfg = new freemarker.template.Configuration();
    cfg.setDirectoryForTemplateLoading(file("templates"));
    Template template = cfg.getTemplate(FileType.FTL.filename(templateName));
    template.process(dataMap, fw);

    return temp;
  }

  private static File file(String resource) {
    return new File(filePath(resource));
  }

  private static String filePath(String resource){
    return ClassLoader.getSystemClassLoader().getResource(resource).getPath();
  }
}
