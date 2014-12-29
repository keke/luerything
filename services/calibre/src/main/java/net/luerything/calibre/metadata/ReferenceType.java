package net.luerything.calibre.metadata;

import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;

/**
 * @author keke
 */
public class ReferenceType implements Serializable {
  private String href;
  private String title;
  private String type;

  @XmlAttribute(name = "href")
  public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  @XmlAttribute(name = "title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @XmlAttribute(name = "type")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
