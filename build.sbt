name := """fileUpload"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  "mysql"%"mysql-connector-java"%"5.1.26",
  "com.amazonaws" % "aws-java-sdk" % "1.3.11",
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
   "org.webjars" %% "webjars-play" % "2.3.0",
  "org.webjars" % "bootstrap" % "3.1.1-2"
)
