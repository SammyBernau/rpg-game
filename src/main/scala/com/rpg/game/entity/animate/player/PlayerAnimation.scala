package com.rpg.game.entity.animate.player

import com.badlogic.gdx.{Gdx, Input}
import com.rpg.game.entity.textures.EntityAnimations
import com.rpg.game.game.config.GameConfig.GameWorld.STATE_TIME
import com.rpg.game.game.config.CurrentWorld
import com.rpg.game.ticksystem.{TickListener, Tick}

class PlayerAnimation(currentWorld: CurrentWorld, tickSystem: Tick) extends TickListener {

  tickSystem.addListener(this)

  private val entityAnimations = EntityAnimations(currentWorld)
  private val playerSkin = entityAnimations.Player
  private var lastDirection = "front"


  override def update(tick: Long): Unit = {
    val playerTexture = currentWorld.mapRenderer.getTextureMapObject("player_animation")
    val playerFixture = currentWorld.mapRenderer.getFixture("player_animation")

    val shift = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)
    if (shift) entityAnimations.frameDuration = 0.1f else entityAnimations.frameDuration = 0.8f

    val num = 2f 
    //2f works when TICK_TIMER_MAX is set to .1f and .2f (in terms of animation not stuttering)

    if (!playerFixture.getBody.getLinearVelocity.isZero(0.1f)) {
      if (playerFixture.getBody.getLinearVelocity.x < 0) {
        playerTexture.setTextureRegion(playerSkin.leftAnimation.getKeyFrame(tick.toFloat/num, true))
        lastDirection = "left"
      } else if (playerFixture.getBody.getLinearVelocity.x > 0) {
        playerTexture.setTextureRegion(playerSkin.rightAnimation.getKeyFrame(tick.toFloat/num, true))
        lastDirection = "right"
      } else if (playerFixture.getBody.getLinearVelocity.y < 0) {
        playerTexture.setTextureRegion(playerSkin.frontAnimation.getKeyFrame(tick.toFloat/num, true))
        lastDirection = "front"
      } else if (playerFixture.getBody.getLinearVelocity.y > 0.016666668) {
        playerTexture.setTextureRegion(playerSkin.backAnimation.getKeyFrame(tick.toFloat/num, true))
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
//  def animate(): Unit = {
//    val playerTexture = currentWorld.mapRenderer.getTextureMapObject("player_animation")
//    val playerFixture = currentWorld.mapRenderer.getFixture("player_animation")
//
//    val shift = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)
//    if (shift) entityAnimations.frameDuration = 0.3f else entityAnimations.frameDuration = 0.5f
//
//    if (!playerFixture.getBody.getLinearVelocity.isZero(0.1f)) {
//      if (playerFixture.getBody.getLinearVelocity.x < 0) {
//        playerTexture.setTextureRegion(playerSkin.leftAnimation.getKeyFrame(STATE_TIME, true))
//        lastDirection = "left"
//      } else if (playerFixture.getBody.getLinearVelocity.x > 0) {
//        playerTexture.setTextureRegion(playerSkin.rightAnimation.getKeyFrame(STATE_TIME, true))
//        lastDirection = "right"
//      } else if (playerFixture.getBody.getLinearVelocity.y < 0) {
//        playerTexture.setTextureRegion(playerSkin.frontAnimation.getKeyFrame(STATE_TIME, true))
//        lastDirection = "front"
//      } else if (playerFixture.getBody.getLinearVelocity.y > 0.016666668) {
//        playerTexture.setTextureRegion(playerSkin.backAnimation.getKeyFrame(STATE_TIME, true))
//        lastDirection = "back"
//      }
//    } else {
//      lastDirection match {
//        case "left" => playerTexture.setTextureRegion(playerSkin.standFramesSorted.get("left_stand").flatMap(tile => Option(tile.getTextureRegion)).getOrElse(playerTexture.getTextureRegion))
//        case "right" => playerTexture.setTextureRegion(playerSkin.standFramesSorted.get("right_stand").flatMap(tile => Option(tile.getTextureRegion)).getOrElse(playerTexture.getTextureRegion))
//        case "front" => playerTexture.setTextureRegion(playerSkin.standFramesSorted.get("front_stand").flatMap(tile => Option(tile.getTextureRegion)).getOrElse(playerTexture.getTextureRegion))
//        case "back" => playerTexture.setTextureRegion(playerSkin.standFramesSorted.get("back_stand").flatMap(tile => Option(tile.getTextureRegion)).getOrElse(playerTexture.getTextureRegion))
//      }
//
//    }
//  }

}
