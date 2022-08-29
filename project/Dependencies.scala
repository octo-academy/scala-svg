import sbt._

object Dependencies {
  private val TestDependencies = Seq(
    D.scalatestWordspec,
    D.scalatestMustmatchers,
    D.scalacheckPlus,
    D.scalacheck
  ).map(_ % Test)

  val Lang: Seq[ModuleID] = TestDependencies

  val Model: Seq[ModuleID] = TestDependencies

  val Dsl: Seq[ModuleID] = TestDependencies

  val Parser: Seq[ModuleID] = TestDependencies

  val Example: Seq[ModuleID] = TestDependencies

  private object V {
    val scalatest      = "3.2.3"
    val scalacheck     = "1.15.2"
    val scalacheckPlus = "3.2.4.0-M1"
  }

  private object D {
    val scalatestWordspec     = "org.scalatest"     %% "scalatest-wordspec"     % V.scalatest
    val scalatestMustmatchers = "org.scalatest"     %% "scalatest-mustmatchers" % V.scalatest
    val scalacheck            = "org.scalacheck"    %% "scalacheck"             % V.scalacheck
    val scalacheckPlus        = "org.scalatestplus" %% "scalacheck-1-15"        % V.scalacheckPlus
  }
}
