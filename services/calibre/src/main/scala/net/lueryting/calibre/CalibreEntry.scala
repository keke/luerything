package net.lueryting.calibre

import java.io.{File, FileReader}
import javax.xml.bind.JAXBContext

import net.luerything.calibre.metadata.PackageType
import org.xml.sax.InputSource

/**
 * An entry of Calibre book
 *
 * @author keke
 * @constructor folder
 */
class CalibreEntry(private val folder: File) {

  /**
   * The { @link PackageType} associated to this Calibre Entry instance
   */
  lazy val calibrePackage: PackageType = {
    CalibreEntry.load(this)
  }

  lazy val cover: Option[File] = {
    val f = new File(folder, "cover.jpg")
    if (f.exists()) Option(f) else None
  }

  def getBooks(filter: => Boolean): List[File] = null

}

object CalibreEntry {
  private val unmarshaller = JAXBContext.newInstance("net.luerything.calibre.metadata").createUnmarshaller()

  def load(entry: CalibreEntry): PackageType = {
    unmarshaller.unmarshal(new InputSource(new FileReader(new File(entry.folder,
      "metadata.opf")))).asInstanceOf[PackageType]
  }
}