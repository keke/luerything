package net.luerything.calibre

import java.io.File

import net.lueryting.calibre.CalibreEntry
import org.scalatest.FlatSpec

class CalibreEntrySpec extends FlatSpec {

  "CalibreEntry" should "has a packageType" in {
    val entry = new CalibreEntry(new File(getClass.getResource("/calibre-test-folder").toURI))
    val packageType = CalibreEntry.load(entry)
    assert(packageType.getMetadata.getTitle == "Functional JavaScript: Introducing Functional Programming With Underscore.js")
  }


  "CalibreEntry" should "has a cover JPB" in {
    val entry = new CalibreEntry(new File(getClass.getResource("/calibre-test-folder").toURI))
    assert(entry.calibrePackage != null)
    assert(entry.cover.isDefined)
  }
}