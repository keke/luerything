package net.lueryting.calibre

import java.io.{File, FileFilter, FileReader}
import javax.xml.bind.JAXBContext

import net.luerything.calibre.metadata.PackageType
import org.apache.commons.io.FilenameUtils
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
  /**
   * The cover (JPG) of the book
   */
  lazy val cover: Option[File] = {
    val f = new File(folder, "cover.jpg")
    if (f.exists()) Option(f) else None
  }

  def getAvailableFormats: Array[String] = {
    val fileName = calibrePackage.getMetadata.getTitle
    folder.listFiles(new FileFilter() {
      override def accept(pathname: File): Boolean = {
        pathname.getName.startsWith(fileName)
      }
    }).map(f => {
      FilenameUtils.getExtension(f.getName).toUpperCase
    })
  }

  def getBook(fmt: String): Option[File] = {
    val f = new File(folder, fmt.toLowerCase)
    if (f.exists()) Some(f) else None
  }
}

object CalibreEntry {
  private val unmarshaller = JAXBContext.newInstance("net.luerything.calibre.metadata").createUnmarshaller()

  def load(entry: CalibreEntry): PackageType = {
    unmarshaller.unmarshal(new InputSource(new FileReader(new File(entry.folder,
      "metadata.opf")))).asInstanceOf[PackageType]
  }
}