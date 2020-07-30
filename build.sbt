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

lazy val fmtFix = TaskKey[Unit](
  label = "fmtFix",
  description = "Format all the source code which includes src, test, and build files",
  rank = KeyRanks.ATask
)
fmtFix := {
  (Compile / scalafmtSbt).value
  (Compile / scalafmt).value
  (Test / scalafmt).value
}

lazy val fmtCheck = TaskKey[Unit](
  label = "fmtCheck",
  description = "Check all the source code which includes src, test, and build files",
  rank = KeyRanks.ATask
)
fmtCheck := {
  (Compile / scalafmtSbtCheck).value
  (Compile / scalafmtCheck).value
  (Test / scalafmtCheck).value
}

Compile / compile := (Compile / compile).dependsOn(fmtFix).value
Test / test := (Test / test).dependsOn(fmtFix).value
