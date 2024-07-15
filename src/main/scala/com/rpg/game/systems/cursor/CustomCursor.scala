package com.rpg.game.systems.cursor

import com.badlogic.gdx.{Gdx, ScreenAdapter}
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.{Pixmap, Texture}
import com.badlogic.gdx.math.{Vector2, Vector3}
import com.rpg.game.config.CurrentMasterConfig



class CustomCursor(currentWorld: CurrentMasterConfig, batch: SpriteBatch) extends ScreenAdapter {

  private val cursorFile = Gdx.files.internal("assets/ui/custom_cursor.png")
  private val customCursor = new Texture(cursorFile)
  //private val spriteBatch = new SpriteBatch()

//  private val player = currentWorld.mapRenderer.getFixture("player_animation")
//  private val playerX = player.getBody.getPosition.x
//  private val playerY = player.getBody.getPosition.y
//  private val camera = currentWorld.viewport.getCamera
//  private var lastValidPos = new Vector2(playerX,playerY)


  /**
   * Hides system cursor and creates a new cursor at the coordinates of where the system cursor would be
   */
  def draw(): Unit = {
    Gdx.input.setCursorCatched(true)
    batch.begin()
    batch.draw(customCursor, Gdx.input.getX.toFloat, Gdx.graphics.getHeight - (Gdx.input.getY.toFloat + customCursor.getHeight))
    batch.end()
  }


//  /**
//   * Tries to limit the range of the custom cursor for straying too far from player
//   */
//  def drawWithRangeLimit(): Unit = {
//    Gdx.input.setCursorCatched(true)
//    batch.begin()
//
//    // Get the current cursor position
//    val cursorPos = new Vector2(Gdx.input.getX.toFloat, Gdx.graphics.getHeight - (Gdx.input.getY.toFloat + customCursor.getHeight))
//
//    // Get the player's current position
//    val playerPos = new Vector2(player.getBody.getPosition.x, player.getBody.getPosition.y)
//
//    // Calculate the direction from the player to the cursor
//    val direction = cursorPos.cpy().sub(playerPos).nor()
//
//    // Calculate the distance between the player and the cursor
//    val distance = cursorPos.dst(playerPos)
//
//    // Define the maximum allowed distance
//    val maxDistance = 100f // Change this to your desired value
//
//    // If the cursor is within the allowed distance, draw it at its current position
//    // Otherwise, draw it at the position on the boundary of the maximum distance in the direction of the cursor from the player
//    if (distance <= maxDistance) {
//      batch.draw(customCursor, cursorPos.x, cursorPos.y)
//    } else {
//      val boundaryPos = playerPos.cpy().add(direction.scl(maxDistance))
//      batch.draw(customCursor, boundaryPos.x, boundaryPos.y)
//    }
//
//    batch.end()
//  }


  /**
   *Sets game cursor to custom sprite using the system cursor
   * https://libgdx.com/wiki/input/cursor-visibility-and-catching
   */
  def set(): Unit = {
    val pixmap = new Pixmap(cursorFile)
    val x = 7
    val y = 7

    val cursor = Gdx.graphics.newCursor(pixmap, x, y)
    Gdx.graphics.setCursor(cursor)
  }

  override def dispose(): Unit = {
    customCursor.dispose()

  }

}
