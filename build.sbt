ThisBuild / version := "0.0.1-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.12"

lazy val root = (project in file("."))
  .settings(
    name := "splick"
  )

val slickVersion = "3.4.1"
val slf4jVersion = "2.0.5"
val scalaTestVersion = "3.2.17"

val dependencies = Seq(
  "com.typesafe.slick" %% "slick" % slickVersion,
  // "com.typesafe.slick" %% "slick-hikaricp" % slickVersion,
  "org.slf4j" % "slf4j-nop" % slf4jVersion,
)

val testDependencies = Seq(
  "org.scalatest" %% "scalatest" % scalaTestVersion
)

libraryDependencies ++=
  dependencies ++
  testDependencies.map(_ % Test)


