/**
 * @author keke
 */
@XmlSchema(namespace = "http://www.idpf.org/2007/opf",
        xmlns = {@XmlNs(prefix = "dc",
                namespaceURI = "http://purl.org/dc/elements/1.1/")},
        elementFormDefault = XmlNsForm.QUALIFIED)
package net.luerything.calibre.metadata;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;