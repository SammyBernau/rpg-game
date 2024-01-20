import java.io.File

import java.nio.charset.StandardCharsets

import sbt.IO._


val libgdxVersion = "1.11.0"
val h2dVersion = "0.1.2"
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
  "com.badlogicgames.gdx" % "gdx" % libgdxVersion,
  "com.badlogicgames.gdx" % "gdx-backend-lwjgl3" % libgdxVersion,
  "com.badlogicgames.gdx" % "gdx-platform" % libgdxVersion,
  "games.rednblack.hyperlap2d" % "runtime-libgdx" % h2dVersion,
  "com.badlogicgames.gdx" % "gdx-box2d" % libgdxVersion,
  "com.badlogicgames.gdx" % "gdx-freetype" % libgdxVersion,
  "net.onedaybeard.artemis" % "artemis-odb" % artemisVersion,
  "com.badlogicgames.gdx" % "gdx-lwjgl3-glfw-awt-macos" % libgdxVersion,
  "com.badlogicgames.gdx" % "gdx-platform" % libgdxVersion classifier "natives-desktop",
  "com.google.inject" % "guice" % "5.1.0"
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
