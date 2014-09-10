package com.pressassociation.events.util;

/**
 * ****************************************************************************************
 *
 * @author <a href="ralph.hodgson@pressassociation.com">Ralph Hodgson</a>
 * @since 08/09/2014 15:42
 * <p/>
 * ****************************************************************************************
 */
public enum FileType {
  CSV, FTL, HTML, TXT, XML;

  public String filename(String filename){
    return filename == null ? null : filename + suffix();
  }

  public String suffix() {
    return "." + this.name().toLowerCase();
  }
}
