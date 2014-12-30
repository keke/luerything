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
    val id = MetadataType.getIdentifier(packageType.getMetadata, "uuid");
    assert(id.getValue == "c032c7e7-c267-40f4-af9b-6f75e5d0ab39")
    assert(id.getIdType == "uuid_id")
    assert(packageType.getGuide != null)
    assert(packageType.getGuide.getReference.getHref == "cover.jpg")
    assert(packageType.getMetadata.getDate != null)
    assert(packageType.getMetadata.getCreators.get(0).getRole == "aut")
  }
}
