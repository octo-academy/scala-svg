ThisBuild / name := "scala-svg"
ThisBuild / version := "0.1"
ThisBuild / scalaVersion := "2.13.3"
ThisBuild / scalacOptions := Seq(
  "-encoding",
  "utf8",
  "-deprecation",
  "-unchecked",
  "-Xlint",
  "-Xfatal-warnings",
  "-feature",
  "-Wunused:imports",
  "-language:implicitConversions",
  "-language:higherKinds",
  "-language:existentials"
)

lazy val svg = project
  .settings(
    libraryDependencies := Dependencies.Svg
  )

lazy val fmtFix = TaskKey[Unit](
  label = "fmtFix",
  description = "Format all the source code which includes src, test, and build files",
  rank = KeyRanks.ATask
)
fmtFix := {
  (scalafmtSbt in Compile).value
  (scalafmt in Compile).value
  (scalafmt in Test).value
}

lazy val fmtCheck = TaskKey[Unit](
  label = "fmtCheck",
  description = "Check all the source code which includes src, test, and build files",
  rank = KeyRanks.ATask
)

fmtCheck := {
  (scalafmtSbtCheck in Compile).value
  (scalafmtCheck in Compile).value
  (scalafmtCheck in Test).value
}

compile in Compile := (compile in Compile).dependsOn(fmtCheck).value
test in Test := (test in Test).dependsOn(fmtCheck).value
