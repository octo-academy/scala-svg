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
