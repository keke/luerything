package net.luerything.calibre.metadata

import javax.xml.bind.JAXBContext

import org.testng.annotations.{BeforeClass, Test}
import org.xml.sax.InputSource

/**
 * @author keke
 */
class CalibreMetadataTest {

  private var jaxb: JAXBContext = null

  @BeforeClass
  def prepareJAXB {
    jaxb = JAXBContext.newInstance("net.luerything.calibre.metadata")
  }

  @Test
  def testBasicOpf: Unit = {
    val packageType = jaxb.createUnmarshaller.unmarshal(new InputSource(getClass.getResourceAsStream("/metadata.opf"))).asInstanceOf[PackageType]
    assert(packageType.getVersion == "2.0")
    assert(packageType.getMetadata.getTitle == "Essentials of Business Analytics-Cengage Learning(2014)")
    assert(packageType.getMetadata.getIdList.size() == 2)
    assert(packageType.getGuide != null)
    assert(packageType.getMetadata.getDate != null)
  }
}
