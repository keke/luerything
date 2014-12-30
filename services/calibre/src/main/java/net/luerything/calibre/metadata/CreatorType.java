package net.luerything.calibre.metadata;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;
import java.io.Serializable;

/**
 * @author keke
 */
public class CreatorType implements Serializable {
  private String role;
  private String fileAs;
  private String value;

  @XmlAttribute(name = "role", namespace = "http://www.idpf.org/2007/opf")
  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  @XmlAttribute(name = "file-as", namespace = "http://www.idpf.org/2007/opf")
  public String getFileAs() {
    return fileAs;
  }

  public void setFileAs(String fileAs) {
    this.fileAs = fileAs;
  }

  @XmlValue
  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
