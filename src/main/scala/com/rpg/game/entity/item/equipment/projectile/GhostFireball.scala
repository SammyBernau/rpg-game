package com.rpg.game.entity.item.equipment.projectile

import com.badlogic.gdx.{Gdx, Input}
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.maps.objects.{RectangleMapObject, TextureMapObject}
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile
import com.badlogic.gdx.math.{MathUtils, Vector2, Vector3}
import com.badlogic.gdx.physics.box2d.{Contact, ContactImpulse, ContactListener, Fixture, Manifold}
import com.rpg.game.entity.UserData
import com.rpg.game.entity.textures.EntityAnimations
import com.rpg.game.game.config.CurrentWorld
import com.rpg.game.game.util.physics.ObjectLayerObject
import com.rpg.game.game.util.physics.collision.Collidable
import org.lwjgl.system.windows.INPUT

class GhostFireball(currentWorld: CurrentWorld) extends Projectile[TiledMapTileMapObject] {

  private val playerFixture = currentWorld.mapRenderer.getFixture("player_animation")
  private val entityAnimations = EntityAnimations(currentWorld)
  private val ghostFireballTile = entityAnimations.GhostFireBall.tile
  private val entityLayer = currentWorld.tiledMap.getLayers.get("entity")
  private var ghostFireballCount = 0

  override def create(): Unit = {
    val LEFT = Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)
    if (LEFT) {
      //Retrieve player's current position
      val playerPosition = playerFixture.getBody.getPosition
      val playerX = playerPosition.x
      val playerY = playerPosition.y

      val mousePosition = getMouseCoordsInWorld

      val spawnDistance = 50

      val angle = calculateAngle(playerX, playerY, mousePosition.x, mousePosition.y)

      //Offsets projectile spawn since they spawn at varying distances based on what quadrant the cursor clicks in (aka unit circle around player)
      val xOffset = 10
      val yOffset = 15

      val spawnX = (playerX + (MathUtils.cos(angle) * spawnDistance)) -xOffset
      val spawnY = (playerY + (MathUtils.sin(angle) * spawnDistance)) -yOffset

      val name = ghostFireballTile.getProperties.get("type").toString + "_" + ghostFireballCount.toString
      //Retrieve bounding box that will be the collision box for fireball
      val rectangleMapObject = ghostFireballTile.getObjects.get(0).asInstanceOf[RectangleMapObject]
      rectangleMapObject.setName(name)
      //Set bounding box just above player's head
      rectangleMapObject.getRectangle.x = spawnX
      rectangleMapObject.getRectangle.y = spawnY

      val obj = new TiledMapTileMapObject(ghostFireballTile, false, false)
      obj.setName(name)
      obj.setX(spawnX)
      obj.setY(spawnY)

      val textureMapObject = obj.asInstanceOf[TextureMapObject]
      setTexturePositionToWorldGridSystem(textureMapObject)

      //add to alive projectiles list
      addProjectile(obj)
      //add to texture and fixture lists to track for updating
      currentWorld.mapRenderer.addNewObjWithTexture(textureMapObject, rectangleMapObject)
      //adds sprite to entity layer to be drawn
      currentWorld.mapRenderer.addToObjectLayer(textureMapObject)
      
      val fixture = currentWorld.mapRenderer.getFixture(name)
      val userData = UserData("GhostFireball", false, name)
      fixture.getBody.setUserData(userData)

      move(name, 200f, angle)

      ghostFireballCount += 1
    }
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
