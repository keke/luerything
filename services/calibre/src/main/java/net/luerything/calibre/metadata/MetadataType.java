package net.luerything.calibre.metadata;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;


/**
 * @author keke
 */
@XmlType(name = "metadata")
public class MetadataType implements Serializable {

    private String title;

    @XmlElement(name = "title", required = true)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
