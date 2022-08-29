import sbt._
import sbt.Keys._

object Settings {

  val Lang: Seq[Def.Setting[_]] = Seq(
    name                 := "scalasvg-lang",
    libraryDependencies ++= Dependencies.Lang
  )

  val Model: Seq[Def.Setting[_]] = Seq(
    name                 := "scalasvg-model",
    libraryDependencies ++= Dependencies.Model
  )

  val Dsl: Seq[Def.Setting[_]] = Seq(
    name                 := "scalasvg-dsl",
    libraryDependencies ++= Dependencies.Dsl
  )

  val Parser: Seq[Def.Setting[_]] = Seq(
    name                 := "scalasvg-parser",
    libraryDependencies ++= Dependencies.Parser
  )

  val Example: Seq[Def.Setting[_]] = Seq(
    name                 := "scalasvg-example",
    libraryDependencies ++= Dependencies.Example
  )

}
