import sbt._

object Dependencies {
  private object Version {
    final val scalatest = "3.2.0"
  }

  final private val `scalatest-wordspec`     = "org.scalatest" %% "scalatest-wordspec"     % Version.scalatest % Test
  final private val `scalatest-mustmatchers` = "org.scalatest" %% "scalatest-mustmatchers" % Version.scalatest % Test

  final val Model = Seq(`scalatest-wordspec`, `scalatest-mustmatchers`)
}
