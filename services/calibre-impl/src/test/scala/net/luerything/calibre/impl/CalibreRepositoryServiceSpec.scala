package net.luerything.calibre.impl

import java.io.File

import net.lueryting.calibre.{RepositoryEvent, RepositoryListener}
import org.apache.commons.io.FileUtils
import org.mockito.Matchers._
import org.mockito.Mockito._
import org.scalatest.mock.MockitoSugar
import org.scalatest.{FlatSpec, Matchers}

/**
 * @author keke
 */
class CalibreRepositoryServiceSpec extends FlatSpec with Matchers with MockitoSugar {
  behavior of "CalibreRepositoryService"
  "CalibreRepositoryService" should "get all entries" in {
    val calRepo = new CalibreRepositoryServiceImpl(new File(getClass.getResource("/test-calibre-root").toURI))
    calRepo.getAllEntries() should have size 4
    calRepo.close
  }


  it should "get notification of repository change" in {
    val calRepo = new CalibreRepositoryServiceImpl(new File(getClass.getResource("/test-calibre-root").toURI))
    val mockListener = mock[RepositoryListener]
    calRepo.watch(mockListener)
    Thread.sleep(1000)
    val file = new File(getClass.getResource("/test-calibre-root").toURI)

    FileUtils.touch(new File(file, "test"))
    println(new File(file, "test").exists() + " has been touched")
    Thread.sleep(10000)
    calRepo.close
    verify(mockListener, times(1)).update(any[RepositoryEvent])
  }
}
