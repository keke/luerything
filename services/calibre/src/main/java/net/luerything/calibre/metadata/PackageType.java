package net.luerything.calibre.metadata;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

/**
 * @author keke
 */
@XmlRootElement(name = "package")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class PackageType implements Serializable {
    private MetadataType metadata;
    private String version;
    private String uniqueIdentify;
    private GuideType guide;

    @XmlAttribute(name = "unique-identifier")
    public String getUniqueIdentify() {
        return uniqueIdentify;
    }

    public void setUniqueIdentify(String uniqueIdentify) {
        this.uniqueIdentify = uniqueIdentify;
    }

    @XmlElement(name = "guide")
    public GuideType getGuide() {
        return guide;
    }

    public void setGuide(GuideType guide) {
        this.guide = guide;
    }

    @XmlAttribute(name = "version")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @XmlElement(name = "metadata")
    public MetadataType getMetadata() {
        return metadata;
    }

    public void setMetadata(MetadataType metadata) {
        this.metadata = metadata;
    }

}
