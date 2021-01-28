ThisBuild / name := "scala-svg"
ThisBuild / version := "0.1"
ThisBuild / scalaVersion := "3.0.0-M3"
ThisBuild / scalacOptions := Seq(
  "-encoding",
  "utf8",
  "-deprecation",
  "-unchecked",
  "-feature",
  "-Xfatal-warnings"
)
ThisBuild / useScala3doc := true

lazy val lang = project
  .settings(libraryDependencies ++= Dependencies.Lang)

lazy val model = project
  .settings(libraryDependencies ++= Dependencies.Model)

lazy val dsl = project
  .aggregate(model)
  .dependsOn(model)
  .settings(libraryDependencies ++= Dependencies.Dsl)

lazy val parser = project
  .aggregate(model)
  .dependsOn(model)
  .settings(libraryDependencies ++= Dependencies.Parser)

lazy val example = project
  .aggregate(model, dsl, parser)
  .dependsOn(model, dsl, parser)
  .settings(libraryDependencies ++= Dependencies.Example)
