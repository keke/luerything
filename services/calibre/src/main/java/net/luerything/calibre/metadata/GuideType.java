package net.luerything.calibre.metadata;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * @author keke
 */
@XmlType(name = "guide")
public class GuideType implements Serializable {
  private ReferenceType reference;

  @XmlElement(name = "reference")
  public ReferenceType getReference() {
    return reference;
  }

  public void setReference(ReferenceType reference) {
    this.reference = reference;
  }
}
