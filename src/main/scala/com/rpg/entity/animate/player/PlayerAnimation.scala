package com.rpg.entity.animate.player

import com.badlogic.gdx.graphics.g2d.Animation.PlayMode
import com.badlogic.gdx.maps.objects.TextureMapObject
import com.badlogic.gdx.{Gdx, Input}
import com.rpg.entity.textures.EntityAnimations
import com.rpg.game.config.map.TiledMapConfig
import com.rpg.game.systems.rendering.services.gameobjects.GameObjectCache
import com.rpg.game.systems.tick.{TickEvent, TickSystem}

import javax.inject.Inject

final class PlayerAnimation @Inject(tiledMapConfig: TiledMapConfig, gameObjectCache: GameObjectCache, val tickSystem: TickSystem) extends TickEvent {
  
  
  //walk/run vars
  private val entityAnimations = EntityAnimations(tiledMapConfig)
  private val playerSkin = entityAnimations.Player
  private var lastDirection = "front"

  //Dodge vars
  var isDodging = false
  private var dodgeAnimationTime = 0f
  private var tickCountAtLastDodge = 0L
  private var dodgeDirection = "front"
  private var cancelOtherDodgeDirections = false

  override def tick(tick: Long): Unit = {
    val playerGameObject = gameObjectCache.get("player_animation").get
    
    val playerTexture = playerGameObject.mapObject.asInstanceOf[TextureMapObject]
    val playerFixture = playerGameObject.fixture

    val w = Gdx.input.isKeyPressed(Input.Keys.W)
    val a = Gdx.input.isKeyPressed(Input.Keys.A)
    val s = Gdx.input.isKeyPressed(Input.Keys.S)
    val d = Gdx.input.isKeyPressed(Input.Keys.D)
    val space = Gdx.input.isKeyPressed(Input.Keys.SPACE)
    val shift = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)

    if (shift) entityAnimations.frameDuration = 0.5f else entityAnimations.frameDuration = .2f

    val frameDivider_ish = 2f //2f works when TICK_TIMER_MAX is set to .1f and .2f (in terms of run/walk animations not stuttering)

    if(space && (tick - tickCountAtLastDodge > 5 || tick <= 5)) {
      tickCountAtLastDodge = 0
      isDodging = true
      dodgeAnimationTime = 0f
    }

    if (isDodging) {
      dodgeAnimationTime += Gdx.graphics.getDeltaTime
      tickCountAtLastDodge = tick
      if(w) {
        playerTexture.setTextureRegion(playerSkin.backDodgeAnimation.getKeyFrame(tick.toFloat / frameDivider_ish, true))
      } else if(a) {
        playerTexture.setTextureRegion(playerSkin.leftDodgeAnimation.getKeyFrame(tick.toFloat / frameDivider_ish, true))
      } else if (s ) {
        playerTexture.setTextureRegion(playerSkin.frontDodgeAnimation.getKeyFrame(tick.toFloat / frameDivider_ish, true))
      } else if (d) {
        playerTexture.setTextureRegion(playerSkin.rightDodgeAnimation.getKeyFrame(tick.toFloat / frameDivider_ish, true))
      }

      if(playerSkin.frontDodgeAnimation.isAnimationFinished(dodgeAnimationTime) ||
        playerSkin.backDodgeAnimation.isAnimationFinished(dodgeAnimationTime) ||
        playerSkin.leftDodgeAnimation.isAnimationFinished(dodgeAnimationTime) ||
        playerSkin.rightDodgeAnimation.isAnimationFinished(dodgeAnimationTime)) {
        isDodging = false
        cancelOtherDodgeDirections = false
      }
    } else {
      if (!playerFixture.getBody.getLinearVelocity.isZero(0.1f)) {
        if (playerFixture.getBody.getLinearVelocity.x < 0) {
          playerTexture.setTextureRegion(playerSkin.leftAnimation.getKeyFrame(tick.toFloat/frameDivider_ish, true))
          lastDirection = "left"
        } else if (playerFixture.getBody.getLinearVelocity.x > 0) {
          playerTexture.setTextureRegion(playerSkin.rightAnimation.getKeyFrame(tick.toFloat/frameDivider_ish, true))
          lastDirection = "right"
        } else if (playerFixture.getBody.getLinearVelocity.y < 0) {
          playerTexture.setTextureRegion(playerSkin.frontAnimation.getKeyFrame(tick.toFloat/frameDivider_ish, true))
          lastDirection = "front"
        } else if (playerFixture.getBody.getLinearVelocity.y > 0.016666668) {
          playerTexture.setTextureRegion(playerSkin.backAnimation.getKeyFrame(tick.toFloat/frameDivider_ish, true))
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

}