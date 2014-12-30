package net.luerything.calibre.metadata;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * @author keke
 */
@XmlType(name = "metadata")
public class MetadataType implements Serializable {

  private String title;
  private Date date;
  private List<Identifier> idList;
  private List<String> subjects;
  private String language;
  private String description;
  private List<CreatorType> creators;

  public static Identifier getIdentifier(MetadataType metadataType, String scheme) {
    for (Identifier id : metadataType.getIdList()) {
      if (id.getScheme().equals(scheme)) {
        return id;
      }
    }
    return null;
  }

  @XmlElement(name = "creator")
  public List<CreatorType> getCreators() {
    return creators;
  }

  public void setCreators(List<CreatorType> creators) {
    this.creators = creators;
  }

  @XmlElement(name = "description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @XmlElement(name = "subject")
  public List<String> getSubjects() {
    return subjects;
  }

  public void setSubjects(List<String> subjects) {
    this.subjects = subjects;
  }

  @XmlElement(name = "language")
  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  @XmlElement(name = "identifier")
  public List<Identifier> getIdList() {
    return idList;
  }

  public void setIdList(List<Identifier> idList) {
    this.idList = idList;
  }

  @XmlElement(name = "date")
  @XmlJavaTypeAdapter(DateAdapter.class)
  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  @XmlElement(name = "title", required = true)
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
