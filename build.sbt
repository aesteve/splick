ThisBuild / version := "0.0.1-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.12"

lazy val root = (project in file("."))
  .settings(
    name := "splick"
  )

val slickVersion = "3.4.1"
val slf4jVersion = "2.0.5"
val scalaTestVersion = "3.2.17"
val sparkVersion = "3.5.0"

val dependencies = Seq(
  "com.typesafe.slick" %% "slick" % slickVersion,
  // "com.typesafe.slick" %% "slick-hikaricp" % slickVersion,
  "org.slf4j" % "slf4j-nop" % slf4jVersion,
)

val testDependencies = Seq(
  "org.scalatest" %% "scalatest" % scalaTestVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion
)

libraryDependencies ++=
  dependencies ++
  testDependencies.map(_ % Test)


ThisBuild / Test / fork := true
ThisBuild / Test / parallelExecution := false
javaOptions ++= Seq(
  "-Xms8G",
  "-Xmx8G",
  "--add-opens=java.base/java.lang=ALL-UNNAMED",
  "--add-opens=java.base/java.lang.invoke=ALL-UNNAMED",
  "--add-opens=java.base/java.lang.reflect=ALL-UNNAMED",
  "--add-opens=java.base/java.io=ALL-UNNAMED",
  "--add-opens=java.base/java.net=ALL-UNNAMED",
  "--add-opens=java.base/java.nio=ALL-UNNAMED",
  "--add-opens=java.base/java.util=ALL-UNNAMED",
  "--add-opens=java.base/java.util.concurrent=ALL-UNNAMED",
  "--add-opens=java.base/java.util.concurrent.atomic=ALL-UNNAMED",
  "--add-opens=java.base/sun.nio.ch=ALL-UNNAMED",
  "--add-opens=java.base/sun.nio.cs=ALL-UNNAMED",
  "--add-opens=java.base/sun.security.action=ALL-UNNAMED",
  "--add-opens=java.base/sun.util.calendar=ALL-UNNAMED",
)