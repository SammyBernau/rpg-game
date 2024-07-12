package com.rpg.entity.animate.player

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.maps.tiled.TiledMapTile
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.{PolygonShape, Shape}
import com.badlogic.gdx.{Gdx, Input}
import com.rpg.entity.animate.entityconstructs.Humanoid
import com.rpg.entity.item.equipment.BaseHumanoidEquipmentSetup
import com.rpg.entity.textures.EntityAnimations
import com.rpg.game.config.CurrentSettings
import com.rpg.game.systems.rendering.{RenderListener, RenderSystem}
import com.rpg.game.systems.tick.{TickListener, TickSystem}

import javax.inject.Inject



//TODO fix jiggling of collision boxes over moving textures
final class PlayerAction @Inject(tickSystem: TickSystem, currentWorld: CurrentSettings) extends TickListener{

  tickSystem.addListener(this)

  override def updateListener(tick: Long): Unit = {
    playerMovement()
    playerCameraZoom()
  }
  
  val player: Player = Player(10, "test", "test", Owner,
      Humanoid("smallballs", 54, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100f, 50f, true, 770, 787,
      BaseHumanoidEquipmentSetup(None, None, None, None, None, None, None, None, None)))

  private def playerMovement(): Unit = {
    val w = Gdx.input.isKeyPressed(Input.Keys.W)
    val a = Gdx.input.isKeyPressed(Input.Keys.A)
    val s = Gdx.input.isKeyPressed(Input.Keys.S)
    val d = Gdx.input.isKeyPressed(Input.Keys.D)
    val space = Gdx.input.isKeyPressed(Input.Keys.SPACE)
    val shift = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)

    var speed = if (shift) player.playerSettings.sprintingSpeed else player.playerSettings.walkingSpeed

    if ((w || s) && (a || d)) {
      speed = speed / Math.sqrt(2.0).toFloat
    }

    val playerFixture = currentWorld.mapRenderer.getFixture("player_animation")
    val filterData = playerFixture.getFilterData


    //TODO -> Complete collision filtering

    //DONT pass a boolean isPlayerDodging, just check for category bits in playerAnimation
//    if(playerDodging) {
//      // Set the category bits to a unique value for the player
//      filterData.categoryBits = 0
//      // Set the mask bits to 0 to prevent collisions with all other bodies
//      filterData.maskBits = 0
//    } else {
//      // Reset the category bits and mask bits to their default values when not dodging
//      filterData.categoryBits = 0x0001
//      filterData.maskBits = -1
//    }

    playerFixture.setFilterData(filterData)


    var x = 0f
    var y = 0f

    if (w) y = y + speed
    if (a) x = x - speed
    if (d) x = x + speed
    if (s) y = y - speed

    val newPosition = new Vector2(x, y)
    playerFixture.getBody.setLinearVelocity(x, y)

//    val lerpFactor = 0.1f
//    val currentPosition = playerFixture.getBody.getPosition
//    val interpolatedPosition = currentPosition.lerp(newPosition, lerpFactor)
//    playerFixture.getBody.setTransform(interpolatedPosition, playerFixture.getBody.getAngle)


    currentWorld.viewport.getCamera.position.set(playerFixture.getBody.getTransform.getPosition.x, playerFixture.getBody.getTransform.getPosition.y, 0)
    currentWorld.viewport.getCamera.update()
  }

  private def playerCameraZoom(): Unit = {
    val up = Gdx.input.isKeyPressed(Input.Keys.NUM_1)
    val down = Gdx.input.isKeyPressed(Input.Keys.NUM_2)

    val camera = currentWorld.viewport.getCamera.asInstanceOf[OrthographicCamera]

    if (up) camera.zoom = camera.zoom - .01f
    if (down) camera.zoom = camera.zoom + .01f

    //TODO -> limit zoom when not testing

    camera.update()
  }

}
