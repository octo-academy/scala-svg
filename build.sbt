ThisBuild / version      := "0.1"
ThisBuild / scalaVersion := "3.1.3"

ThisBuild / scalacOptions := Seq(
  "-encoding",
  "utf8",
  "-deprecation",
  "-unchecked",
  "-feature",
  "-Xfatal-warnings"
)

lazy val lang = project
  .in(file("modules/lang"))
  .settings(Settings.Lang)

lazy val model = project
  .in(file("modules/model"))
  .settings(Settings.Model)

lazy val dsl = project
  .in(file("modules/dsl"))
  .aggregate(model)
  .dependsOn(model)
  .settings(Settings.Dsl)

lazy val parser = project
  .in(file("modules/parser"))
  .aggregate(lang, model)
  .dependsOn(lang, model)
  .settings(Settings.Parser)

lazy val example = project
  .in(file("example"))
  .aggregate(model, dsl, parser)
  .dependsOn(model, dsl, parser)
  .settings(Settings.Example)
