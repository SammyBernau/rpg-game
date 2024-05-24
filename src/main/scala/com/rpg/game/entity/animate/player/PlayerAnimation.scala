package com.rpg.game.entity.animate.player

import com.badlogic.gdx.{Gdx, Input}
import com.rpg.game.game.config.GameConfig.GameWorld.STATE_TIME
import com.rpg.game.entity.animate.EntityAnimations
import com.rpg.game.game.config.CurrentWorld

class PlayerAnimation(currentWorld: CurrentWorld) {

  private val entityAnimations = EntityAnimations(currentWorld)
  private val playerSkin = entityAnimations.Player
  private var lastDirection = "front"
  
  
  def animate(): Unit = {
    val playerTexture = currentWorld.mapRenderer.getTextureMapObject("player_animation")
    val playerFixture = currentWorld.mapRenderer.getFixture("player_animation")

    val shift = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)
    if (shift) entityAnimations.frameDuration = 0.3f else entityAnimations.frameDuration = 0.5f

    if (!playerFixture.getBody.getLinearVelocity.isZero(0.1f)) {
      if (playerFixture.getBody.getLinearVelocity.x < 0) {
        playerTexture.setTextureRegion(playerSkin.leftAnimation.getKeyFrame(STATE_TIME, true))
        lastDirection = "left"
      } else if (playerFixture.getBody.getLinearVelocity.x > 0) {
        playerTexture.setTextureRegion(playerSkin.rightAnimation.getKeyFrame(STATE_TIME, true))
        lastDirection = "right"
      } else if (playerFixture.getBody.getLinearVelocity.y < 0) {
        playerTexture.setTextureRegion(playerSkin.frontAnimation.getKeyFrame(STATE_TIME, true))
        lastDirection = "front"
      } else if (playerFixture.getBody.getLinearVelocity.y > 0.016666668) {
        playerTexture.setTextureRegion(playerSkin.backAnimation.getKeyFrame(STATE_TIME, true))
        lastDirection = "back"
      }
    } else {
      lastDirection match {
        case "left" => playerTexture.setTextureRegion(playerSkin.standFramesSorted.get("left_stand").flatMap(tile => Option(tile.getTextureRegion)).getOrElse(playerTexture.getTextureRegion))
        case "right" => playerTexture.setTextureRegion(playerSkin.standFramesSorted.get("right_stand").flatMap(tile => Option(tile.getTextureRegion)).getOrElse(playerTexture.getTextureRegion))
        case "front" => playerTexture.setTextureRegion(playerSkin.standFramesSorted.get("front_stand").flatMap(tile => Option(tile.getTextureRegion)).getOrElse(playerTexture.getTextureRegion))
        case "back" => playerTexture.setTextureRegion(playerSkin.standFramesSorted.get("back_stand").flatMap(tile => Option(tile.getTextureRegion)).getOrElse(playerTexture.getTextureRegion))
      }

    }
  }

}
