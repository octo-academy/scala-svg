ThisBuild / name := "scala-svg"
ThisBuild / version := "0.1"
ThisBuild / scalaVersion := "0.26.0-RC1"
ThisBuild / scalacOptions := Seq(
  "-encoding",
  "utf8",
  "-deprecation",
  "-unchecked",
  "-Xfatal-warnings",
  "-feature"
)

lazy val model = project
  .settings(
    crossScalaVersions := Seq(scalaVersion.value, "2.13.3")
  )

lazy val dsl = project
  .settings(
    crossScalaVersions := Seq(scalaVersion.value, "2.13.3")
  )
  
lazy val parser = project
.settings(
  crossScalaVersions := Seq(scalaVersion.value, "2.13.3")
)
