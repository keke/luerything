package net.luerything.calibre

import java.io.File

import net.lueryting.calibre.CalibreEntry
import org.testng.annotations.Test

class CalibreEntryTest {
  @Test
  def createPackage {
    val entry = new CalibreEntry(new File(getClass.getResource("/calibre-test-folder").toURI))
    val packageType = CalibreEntry.load(entry)
    assert(packageType.getMetadata.getTitle == "Functional JavaScript: Introducing Functional Programming With Underscore.js")
  }

  @Test
  def getPackage {
    val entry = new CalibreEntry(new File(getClass.getResource("/calibre-test-folder").toURI))
    assert(entry.calibrePackage != null)
    assert (entry.cover.isDefined)
  }
}