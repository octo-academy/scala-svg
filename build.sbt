ThisBuild / name := "scala-svg"
ThisBuild / version := "0.1"
ThisBuild / scalaVersion := "0.26.0-RC1"
ThisBuild / scalacOptions := Seq(
  "-encoding",
  "utf8",
  "-deprecation",
  "-unchecked",
  "-feature",
  "-Xfatal-warnings"
)

lazy val model = project

lazy val dsl = project
  .aggregate(model)
  .dependsOn(model)

lazy val parser = project
  .aggregate(model)
  .dependsOn(model)
