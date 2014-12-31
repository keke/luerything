package net.luerything.calibre.impl

import javax.xml.bind.JAXBContext

import net.luerything.calibre.metadata.PackageType
import org.xml.sax.InputSource

/**
 * @author keke
 */
object PlayMetadataReader {

  def main(args: Array[String]): Unit = {
    val jaxb = JAXBContext.newInstance("net.luerything.calibre.metadata")
    val calibrePackage = jaxb.createUnmarshaller().unmarshal(new InputSource(PlayMetadataReader.getClass.getResourceAsStream("/metadata.opf"))).asInstanceOf[PackageType]

    println(calibrePackage.getVersion + calibrePackage.getMetadata.getTitle + calibrePackage.getMetadata.getDate)
  }
}
