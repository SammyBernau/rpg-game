package com.rpg.game.entity.animate.player

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.{PolygonShape, Shape}
import com.badlogic.gdx.{Gdx, Input}
import com.rpg.game.entity.animate.Humanoid
import com.rpg.game.entity.item.equipment.BaseHumanoidEquipmentSetup
import com.rpg.game.game.config.CurrentWorld
import com.rpg.game.game.util.OrthogonalTiledMapRendererWithObjects

class PlayerAction(currentWorld: CurrentWorld) {

  private val player: Player = Player(10, "test", "test", Owner,
      Humanoid("smallballs", 54, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 200f, 100f, true, 770, 787,
      BaseHumanoidEquipmentSetup(None, None, None, None, None, None, None, None, None)))
  def playerMovement(): Unit = {
    val w = Gdx.input.isKeyPressed(Input.Keys.W)
    val a = Gdx.input.isKeyPressed(Input.Keys.A)
    val s = Gdx.input.isKeyPressed(Input.Keys.S)
    val d = Gdx.input.isKeyPressed(Input.Keys.D)

    val shift = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)
    var speed = if (shift) player.playerSettings.sprintingSpeed else player.playerSettings.walkingSpeed

    if ((w || s) && (a || d)) {
      speed = speed / Math.sqrt(2.0).toFloat
    }

    val playerTexture = currentWorld.mapRenderer.getTextureMapObject("player_entity")
    val playerFixture = currentWorld.mapRenderer.getFixture("player_entity")

    //    val width = playerTexture.getTextureRegion.getRegionWidth / 2
    //    val height = playerTexture.getTextureRegion.getRegionHeight / 2
    val (width, height) = getPolygonShapeDimensions(playerFixture.getShape)

//    var x = playerFixture.getBody.getPosition.x
//    var y = playerFixture.getBody.getPosition.y

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

//    val playerTextureOriginX = playerTexture.getOriginX
//    val playerTextureOriginY = playerTexture.getOriginY
//    
//    playerTexture.setX((playerFixture.getBody.getTransform.getPosition.x - playerTextureOriginX) - (width / 2))
//    playerTexture.setY((playerFixture.getBody.getTransform.getPosition.y - playerTextureOriginY) - (height / 2))
  }

  private def getPolygonShapeDimensions(shape: Shape): (Float, Float) = {
    val poly = shape.asInstanceOf[PolygonShape]

    val vertices = Array.fill(poly.getVertexCount)(new Vector2())

    for (i <- 0 until poly.getVertexCount) {
      poly.getVertex(i, vertices(i))
    }

    val minX = vertices.minBy(_.x).x
    val maxX = vertices.maxBy(_.x).x
    val minY = vertices.minBy(_.y).y
    val maxY = vertices.maxBy(_.y).y

    val width = maxX - minX
    val height = maxY - minY

    (width, height)
  }

  def playerCameraZoom(): Unit = {
    val up = Gdx.input.isKeyPressed(Input.Keys.NUM_1)
    val down = Gdx.input.isKeyPressed(Input.Keys.NUM_2)

    val camera = currentWorld.viewport.getCamera.asInstanceOf[OrthographicCamera]

    if (up) camera.zoom = camera.zoom - .01f
    if (down) camera.zoom = camera.zoom + .01f

    camera.update()
  }

}
