import sbt._

object Dependencies {
  private object Version {
    final val scalatest = "3.2.0"
  }

  private val `scalatest-wordspec`     = "org.scalatest" % "scalatest-wordspec"     % Version.scalatest % Test
  private val `scalatest-mustmatchers` = "org.scalatest" % "scalatest-mustmatchers" % Version.scalatest % Test

  /* @todo: Enable test dependencies once the compilcation error on the library side is fixed */
  // val Model = Seq(`scalatest-wordspec`, `scalatest-mustmatchers`)
}
