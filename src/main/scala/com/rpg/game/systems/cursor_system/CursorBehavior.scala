package com.rpg.game.systems.cursor_system

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.math.{Vector2, Vector3}
import com.badlogic.gdx.physics.box2d.Fixture
import com.rpg.game.config.CurrentSettings

import scala.math.sqrt

class CursorBehavior(currentWorld: CurrentSettings) {

  private val player = currentWorld.mapRenderer.getFixture("player_animation")
  private val playerX = player.getBody.getPosition.x
  private val playerY = player.getBody.getPosition.y
  private val camera = currentWorld.viewport.getCamera
  private var lastValidPos = new Vector2(playerX,playerY)


  private val screenCoords = new Vector3(Gdx.input.getX.toFloat, Gdx.input.getY.toFloat,0)
  private val worldCoords = currentWorld.viewport.getCamera.unproject(screenCoords)

//  def limitMouseRangeMine(maxRange: Float): Unit = {
//    // Get player's position
//    val playerPos = player.getBody.getPosition
//
//    // Get mouse position
//    val screenMousePos = new Vector3(Gdx.input.getX.toFloat, Gdx.input.getY.toFloat, 0)
//
//    val worldMousePosUnproj = camera.unproject(screenMousePos)
//
//    val worldMousePos = new Vector2(worldMousePosUnproj.x, worldMousePosUnproj.y)
//
//    // Calculate distance between player and mouse
//    val distance = playerPos.dst(worldMousePos)
//
//    if(distance <= maxRange) lastValidPos = new Vector2(worldMousePosUnproj.x,worldMousePosUnproj.y)
//
//    // If the distance is greater than the max range
//    if (distance > maxRange) {
//
//      val newMousePosVec3 = new Vector3(lastValidPos.x, lastValidPos.y, 0)
//
//      val screenNewMousePos = camera.project(newMousePosVec3)
//
//      // Set the new mouse position
//      Gdx.input.setCursorPosition((screenNewMousePos.x).toInt, (screenNewMousePos.y).toInt)
//    }
//  }

  def limitMouseRangeMine(maxRange: Float): Unit = {
    // Get player's position
    val playerPos = player.getBody.getPosition

    // Get mouse position
    val screenMousePos = new Vector3(Gdx.input.getX.toFloat, Gdx.input.getY.toFloat, 0)
    val worldMousePosUnproj = camera.unproject(screenMousePos)
    val worldMousePos = new Vector2(worldMousePosUnproj.x, worldMousePosUnproj.y)

    // Calculate distance between player and mouse
    val distance = playerPos.dst(worldMousePos)

    // If the distance is greater than the max range
    if (distance > maxRange) {
      // Calculate the direction from the player to the mouse
      val direction = worldMousePos.sub(playerPos).nor()

      // Calculate the new position on the boundary of the circle
      val newPos = playerPos.add(direction.scl(maxRange))

      val newMousePosVec3 = new Vector3(newPos.x, newPos.y, 0)
      val screenNewMousePos = camera.project(newMousePosVec3)

      // Set the new mouse position
      Gdx.input.setCursorPosition((screenNewMousePos.x).toInt, (screenNewMousePos.y).toInt)
    } else {
      lastValidPos = new Vector2(worldMousePosUnproj.x, worldMousePosUnproj.y)
    }
  }

//  def limitCursorRange(maxRange: Float): Unit = {
//    // Get player's position
//    val playerPos = player.getBody.getPosition
//
//    // Get mouse position
//    val screenMousePos = new Vector3(Gdx.input.getX.toFloat, Gdx.graphics.getHeight - (Gdx.input.getY.toFloat + texture.getHeight), 0)
//    val worldMousePosUnproj = camera.unproject(screenMousePos)
//    val worldMousePos = new Vector2(worldMousePosUnproj.x, worldMousePosUnproj.y)
//
//    // Calculate distance between player and mouse
//    val distance = playerPos.dst(worldMousePos)
//
//    // If the distance is greater than the max range
//    if (distance > maxRange) {
//      // Calculate the direction from the player to the mouse
//      val direction = worldMousePos.sub(playerPos).nor()
//
//      // Calculate the new position on the boundary of the circle
//      val newPos = playerPos.add(direction.scl(maxRange))
//
//      val newMousePosVec3 = new Vector3(newPos.x, newPos.y, 0)
//      val screenNewMousePos = camera.project(newMousePosVec3)
//
//      // Set the new mouse position
//      Gdx.input.setCursorPosition((screenNewMousePos.x).toInt, (screenNewMousePos.y).toInt)
//    } else {
//      lastValidPos = new Vector2(worldMousePosUnproj.x, worldMousePosUnproj.y)
//    }
//  }




}
