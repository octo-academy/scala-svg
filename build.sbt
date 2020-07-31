ThisBuild / name := "scala-svg"
ThisBuild / version := "0.1"
ThisBuild / scalaVersion := "0.24.0-RC1"
ThisBuild / scalacOptions := Seq(
  "-encoding",
  "utf8",
  "-deprecation",
  "-unchecked",
  "-feature",
  "-Xfatal-warnings"
)

lazy val model = project
  .settings(CommonSettings)
  .settings(
    Test / scalacOptions += "-language:implicitConversions",
    libraryDependencies := Dependencies.Model
  )

lazy val dsl = project
  .aggregate(model)
  .dependsOn(model)
  .settings(CommonSettings)

lazy val parser = project
  .aggregate(model)
  .dependsOn(model)
  .settings(CommonSettings)

lazy val CommonSettings = Seq(
  crossScalaVersions := Seq(scalaVersion.value, "2.13.3")
)
