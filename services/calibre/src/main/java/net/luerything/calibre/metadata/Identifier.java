package net.luerything.calibre.metadata;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;
import java.io.Serializable;

/**
 * @author keke
 */
public class Identifier implements Serializable {
  private String scheme;
  private String idType;
  private String value;

  @XmlAttribute(name = "scheme")
  public String getScheme() {
    return scheme;
  }

  public void setScheme(String scheme) {
    this.scheme = scheme;
  }

  @XmlAttribute(name = "id")
  public String getIdType() {
    return idType;
  }

  public void setIdType(String idType) {
    this.idType = idType;
  }

  @XmlValue
  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
