package com.rpg.entity.item.projectiles.projectile_systems

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.maps.objects.{RectangleMapObject, TextureMapObject}
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile
import com.badlogic.gdx.math.{MathUtils, Rectangle, Vector2, Vector3}
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.{Gdx, Input}
import com.rpg.entity.ObjectUserData
import com.rpg.entity.item.projectiles.{Projectile, ProjectileSystem}
import com.rpg.entity.textures.EntityAnimations
import com.rpg.game.config.CurrentSettings
import com.rpg.game.systems.physics.World.WORLD
import com.rpg.game.systems.physics.collision.Collidable
import com.rpg.game.systems.physics.world.PhysicsObjectProducer
import com.rpg.game.systems.tick.{TickListener, TickSystem}
import org.lwjgl.system.windows.INPUT

import javax.inject.Inject

final class GhostFireballSystem @Inject(tickSystem: TickSystem,currentWorld: CurrentSettings) extends Projectile with TickListener {
  //Add to listener list
  tickSystem.addListener(this)

  private val playerFixture = currentWorld.mapRenderer.getFixture("player_animation")
  private val entityAnimations = EntityAnimations(currentWorld)
  private val ghostFireballTile = entityAnimations.GhostFireBall.tile
  private val entityLayer = currentWorld.tiledMap.getLayers.get("entity")
  private val SPAWN_DISTANCE = 50

  private var ghostFireballCount = 0
  private var tickAtLastShot = 0L

  override def updateListener(tick: Long): Unit = {
    val LEFT = Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)
    if (LEFT && (tick - tickAtLastShot > 1 || tick <= 1)) {
      create()
      tickAtLastShot = tick
    }
  }

  override def create(): Unit = {
    //Retrieve player's current position
    val playerPosition = playerFixture.getBody.getPosition
    val playerX = playerPosition.x
    val playerY = playerPosition.y
    
    val mousePosition = getMouseCoordsInWorld

    val angle = calculateAngle(playerX, playerY, mousePosition.x, mousePosition.y)

    //Offsets projectile spawn since they spawn at varying distances based on what quadrant the cursor clicks in (aka unit circle around player)
    val xOffset = 10
    val yOffset = 15

    val spawnX = (playerX + (MathUtils.cos(angle) * SPAWN_DISTANCE)) - xOffset
    val spawnY = (playerY + (MathUtils.sin(angle) * SPAWN_DISTANCE)) - yOffset

    //Set bounding box just above player's head
    val name = ghostFireballTile.getProperties.get("type").toString + "_" + ghostFireballCount.toString
    //Retrieve bounding box that will be the collision box for fireball
    val rectangleMapObject = ghostFireballTile.getObjects.get(0).asInstanceOf[RectangleMapObject]
    rectangleMapObject.setName(name)
    rectangleMapObject.getRectangle.x = spawnX
    rectangleMapObject.getRectangle.y = spawnY


    val obj = new TiledMapTileMapObject(ghostFireballTile, false, false)
    obj.setName(name)
    obj.setX(spawnX)
    obj.setY(spawnY)

    //correct texture position and set rotation of texture
    val textureMapObject = obj.asInstanceOf[TextureMapObject]
    setTexturePositionToWorldGridSystem(textureMapObject)
    textureMapObject.setRotation(angle)

    //add to texture and fixture lists to track for updating
    currentWorld.mapRenderer.addNewObjWithTexture(textureMapObject, rectangleMapObject)
    //adds sprite to entity layer to be drawn
    currentWorld.mapRenderer.addToObjectLayer(textureMapObject)

    //retrieve physics body of fireball
    val fixture = currentWorld.mapRenderer.getFixture(name)
    //set rotation of fireball physics body
    fixture.getBody.setTransform(fixture.getBody.getPosition, angle - (MathUtils.degreesToRadians * 90))

    val userData = ObjectUserData("GhostFireball", false, name)
    fixture.getBody.setUserData(userData)

    move(name, 200f, angle)

    ghostFireballCount += 1
  }

  private def move(name: String, speed: Float, angle: Float): Unit = {
    val mousePosition = getMouseCoordsInWorld

    //Retrieve fireball's current position
    val fireballFixture = currentWorld.mapRenderer.getFixture(name)
    val fireballBody = fireballFixture.getBody
    val fireballPosition = fireballBody.getPosition

    //Stops ball from slowing down over time (constant speed)
    fireballBody.setLinearDamping(0f)

    //Calculate the force to be applied on the fireball
    val forceX = MathUtils.cos(angle) * speed
    val forceY = MathUtils.sin(angle) * speed

    //Apply the force to the fireball
    fireballBody.setLinearVelocity(forceX, forceY)
  }

  private def setTexturePositionToWorldGridSystem(textureMapObject: TextureMapObject): Unit = {
    val x = textureMapObject.getX
    val y = textureMapObject.getY
    val screenPosition = Vector3(x, y, 0)
    val worldPosition = currentWorld.viewport.getCamera.unproject(screenPosition)

    textureMapObject.setX(worldPosition.x)
    textureMapObject.setY(worldPosition.y)
  }

  private def calculateAngle(x1: Float, y1: Float, x2: Float, y2: Float): Float = MathUtils.atan2(y2 - y1, x2 - x1)

  private def getMouseCoordsInWorld: Vector2 = currentWorld.viewport.unproject(Vector2(Gdx.input.getX.toFloat, Gdx.input.getY.toFloat))

}
