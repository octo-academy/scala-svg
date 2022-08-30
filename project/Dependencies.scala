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
    val scalatest      = "3.2.12"
    val scalacheck     = "1.16.0"
    val scalacheckPlus = "3.2.11.0"
  }

  private object D {
    val scalatestWordspec     = "org.scalatest"     %% "scalatest-wordspec"     % V.scalatest
    val scalatestMustmatchers = "org.scalatest"     %% "scalatest-mustmatchers" % V.scalatest
    val scalacheck            = "org.scalacheck"    %% "scalacheck"             % V.scalacheck
    val scalacheckPlus        = "org.scalatestplus" %% "scalacheck-1-15"        % V.scalacheckPlus
  }

}
