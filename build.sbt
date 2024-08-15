import java.io.File

import java.nio.charset.StandardCharsets

import sbt.IO._


val libGdxVersion = "1.12.1"
val artemisVersion = "2.3.0"
logLevel := Level.Debug

name := "rpg_game"
version := "0.1"
scalaVersion := "3.2.0"
assetsDirectory := {
  val r = file("assets")
  IO.createDirectory(r)
  r
}

libraryDependencies ++= Seq(
  "com.badlogicgames.gdx" % "gdx" % libGdxVersion,
  "com.badlogicgames.gdx" % "gdx-backend-lwjgl3" % libGdxVersion,
  "com.badlogicgames.gdx" % "gdx-platform" % libGdxVersion,
  "com.badlogicgames.gdx" % "gdx-box2d-platform" % libGdxVersion classifier "natives-desktop",
  "com.badlogicgames.gdx" % "gdx-freetype" % libGdxVersion,
  "com.badlogicgames.gdx" % "gdx-box2d" % libGdxVersion,
  "com.badlogicgames.gdx" % "gdx-lwjgl3-glfw-awt-macos" % libGdxVersion,
  "com.badlogicgames.gdx" % "gdx-platform" % libGdxVersion classifier "natives-desktop",
  "com.badlogicgames.gdx" % "gdx-jnigen-loader" % "2.3.1",
  "net.onedaybeard.artemis" % "artemis-odb" % artemisVersion,
  "com.google.inject" % "guice" % "5.1.0",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.14.2"
)
javaOptions ++= Seq(
  "-XstartOnFirstThread"
)
javacOptions ++= Seq(
  "-Xlint",
  "-encoding", "UTF-8",
  "-source", "17",
  "-target", "17"
)
scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-feature",
  "-encoding", "UTF-8",
)
exportJars := true

Compile / fork := true
run / baseDirectory := assetsDirectory.value
Compile / unmanagedResourceDirectories += assetsDirectory.value

lazy val assetsDirectory = settingKey[File]("Directory with game's assets")