package net.luerything.calibre.metadata

import javax.xml.bind.JAXBContext

import org.scalatest.{FlatSpec, Matchers}
import org.xml.sax.InputSource

/**
 * @author keke
 */
class CalibreMetadataSpec extends FlatSpec with Matchers {

  private var jaxb: JAXBContext = null

  def fixture = prepareJAXB

  def prepareJAXB: JAXBContext = {
    JAXBContext.newInstance("net.luerything.calibre.metadata")
  }

  behavior of "Calibre metadata"

  "CalibreMetadata" should "be unmarshalled" in {
    val packageType = fixture.createUnmarshaller.unmarshal(new InputSource(getClass.getResourceAsStream("/metadata.opf"))).asInstanceOf[PackageType]
    packageType should not be (null)
  }

  def withPackageType(code: PackageType => Any) = {
    val packageType = fixture.createUnmarshaller.unmarshal(new InputSource(getClass.getResourceAsStream("/metadata.opf"))).asInstanceOf[PackageType]
    code(packageType)
  }

  it should "has version with 2.0" in withPackageType { packageType =>
    packageType.getVersion should be("2.0")
  }

  it should "have title" in withPackageType { pt =>
    pt.getMetadata.getTitle should be("Essentials of Business Analytics-Cengage Learning(2014)")
  }

  it should "have 2 identifies" in withPackageType { pt =>
    pt.getMetadata.getIdList should have size 2
    val id = MetadataType.getIdentifier(pt.getMetadata, "uuid");
    id.getValue should be("c032c7e7-c267-40f4-af9b-6f75e5d0ab39")
    id.getIdType should be("uuid_id")
  }

  it should "have cover" in withPackageType { pt =>
    pt.getGuide.getReference.getHref should be("cover.jpg")
  }

  it should "have a date" in withPackageType { pt =>
    pt.getMetadata.getDate should not be (null)
  }

  it should "have a creator with role" in withPackageType { pt =>
    pt.getMetadata.getCreators.get(0).getRole should be("aut")
  }
}
