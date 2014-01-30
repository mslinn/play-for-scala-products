import sbt.Keys._
import play.Project._
import java.io.File
import play.core.PlayVersion.{current => playVersion}

organization := "com.micronautics"

name := "barcode"

version := "0.1.0"

scalaVersion := "2.10.3"

scalacOptions ++= Seq("-deprecation", "-encoding", "UTF-8", "-feature", "-target:jvm-1.7", "-unchecked",
    "-Ywarn-adapted-args", "-Ywarn-value-discard", "-Xlint")

scalacOptions in (Compile, doc) <++= baseDirectory.map {
  (bd: File) => Seq[String](
     "-sourcepath", bd.getAbsolutePath,
     "-doc-source-url", "https://github.com/mslinn/barcode/tree/master€{FILE_PATH}.scala"
  )
}

javacOptions ++= Seq("-Xlint:deprecation", "-Xlint:unchecked", "-source", "1.7", "-target", "1.7", "-g:vars")

resolvers ++= Seq(
  "Typesafe Releases"   at "http://repo.typesafe.com/typesafe/releases"
)

libraryDependencies ++= Seq(
  "com.typesafe"            %  "config"          % "1.0.1" withSources,
  "org.webjars"             %% "webjars-play"    % "2.2.0",
  "com.typesafe.play"       %% "play"            % playVersion,
  "com.typesafe.play"       %% "anorm"           % playVersion,
  "com.typesafe.play"       %% "play-jdbc"       % playVersion,
  "com.typesafe.play"       %% "play-json"       % playVersion,
  "net.sf.barcode4j"        %  "barcode4j"       % "2.1",
  "com.github.nscala-time"  %% "nscala-time"     % "0.6.0" withSources,
  //
  "junit"                   %  "junit"           % "4.8.1"  % "test",
  "com.typesafe.play"       %% "play-test"       % playVersion % "test",
  "org.scalatest"           %% "scalatest"       % "2.0" % "test" withSources
)

play.Project.playScalaSettings

logLevel := Level.Warn

// define the statements initially evaluated when entering 'console', 'console-quick', or 'console-project'
initialCommands := """|Thread.currentThread.setContextClassLoader(getClass.getClassLoader)
                      |new play.core.StaticApplication(new java.io.File("."))
                      |
                      |import com.github.tototoshi.slick.JodaSupport._
                      |import java.net.URL
                      |import java.text.DateFormat
                      |import java.util.Locale
                      |import org.joda.time._
                      |import org.joda.time.format.{DateTimeFormat, DateTimeFormatter}
                      |import play.api.db.DB
                      |import play.Logger
                      |""".stripMargin

// Only show warnings and errors on the screen for compilations.
// This applies to both test:compile and compile and is Info by default
logLevel in compile := Level.Warn

cancelable := true

sublimeTransitive := true

logBuffered in Test := false

Keys.fork in Test := false

parallelExecution in Test := false
