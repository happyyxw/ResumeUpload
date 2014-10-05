import sbt._
import Keys._

object ApplicationBuild extends Build {

    val appName         = "devcenter-java-play-s3"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      "com.amazonaws" % "aws-java-sdk" % "1.3.11"
    )

}