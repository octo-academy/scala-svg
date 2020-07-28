import sbt._

object Dependencies {
  private final object Version {
    final val scalatest = "3.1.1"
  }

  final private val scalatest = "org.scalatest" %% "scalatest" % Version.scalatest

  final val Svg = Seq(scalatest % Test)
}
