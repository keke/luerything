package net.luerything.calibre.impl

import java.io.File
import java.nio.file.{FileSystems, Paths}

/**
 * @author keke
 */
object PlayRepository {
  def main(args: Array[String]): Unit = {

    val repo = new CalibreRepositoryServiceImpl(new File("/Users/keke/Calibre Library"))
    repo.getAllEntries().foreach(p => {
      println(p.calibrePackage.getMetadata.getTitle)
    })
    repo.close
  }
}
