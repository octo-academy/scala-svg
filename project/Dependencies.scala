import sbt._

object Dependencies {
  private val TestDependencies = Seq(
    D.scalatestWordspec,
    D.scalatestMustmatchers
  ).map(_ % Test)

  val Lang: Seq[ModuleID] = TestDependencies

  val Model: Seq[ModuleID] = TestDependencies

  val Dsl: Seq[ModuleID] = TestDependencies

  val Parser: Seq[ModuleID] = TestDependencies

  val Example: Seq[ModuleID] = TestDependencies

  private object V {
    val scalatest = "3.2.3"
  }

  private object D {
    val scalatestWordspec     = "org.scalatest" %% "scalatest-wordspec"     % V.scalatest
    val scalatestMustmatchers = "org.scalatest" %% "scalatest-mustmatchers" % V.scalatest
  }
}
