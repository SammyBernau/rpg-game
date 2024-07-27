package com.rpg.entity.item.projectiles.projectile_systems

import com.badlogic.gdx.maps.objects.{RectangleMapObject, TextureMapObject}
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject
import com.badlogic.gdx.math.{MathUtils, Rectangle, Vector2, Vector3}
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.{Gdx, Input}
import com.rpg.entity.item.projectiles.{Projectile, ProjectileSystem}
import com.rpg.entity.textures.EntityAnimations
import com.rpg.game.config.map.TiledMapConfig
import com.rpg.game.systems.physics.world.ObjectData
import com.rpg.game.systems.rendering.services.gameobjects.{GameObject, GameObjectCache, ObjectRenderingServiceHandler}
import com.rpg.game.systems.tick.{TickEvent, TickSystem}
import org.lwjgl.system.windows.INPUT

import javax.inject.Inject

final class GhostFireballSystem @Inject(val tickSystem: TickSystem, 
                                        tiledMapConfig: TiledMapConfig,
                                        projectileMoveCache: ProjectileMoveCache,
                                        objectRenderingServiceHandler: ObjectRenderingServiceHandler,
                                        gameObjectCache: GameObjectCache) extends Projectile with TickEvent {
  
  private val playerGameObject = gameObjectCache.get("player_animation").get
  private val playerFixture = playerGameObject.fixture

  //Fireball animation
  private val entityAnimations = EntityAnimations(tiledMapConfig)
  private val ghostFireballTile = entityAnimations.GhostFireBall.tile
  private val SPAWN_DISTANCE = 50
  private val SPEED = 200f
  private var ghostFireballCount = 0
  private var tickAtLastShot = 0L

  override def tick(tick: Long): Unit = {
    val LEFT = Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)
    if (LEFT && (tick - tickAtLastShot > 1 || tick <= 1)) {
      create()
      tickAtLastShot = tick
    }
  }

  override def create(): Unit = synchronized {
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

    //This object is the one that holds both rectangleMapObject and TextureMapObject
    val tiledMapTileMapObject = new TiledMapTileMapObject(ghostFireballTile, false, false)
    tiledMapTileMapObject.setName(name)
    tiledMapTileMapObject.getProperties.put("BodyType", "Dynamic")

    //textureMapObject holds the correct position of the bounding box and texture
    val textureMapObject = tiledMapTileMapObject.asInstanceOf[TextureMapObject]
    setTexturePositionToWorldGridSystem(textureMapObject)

    //textureMapObject.setRotation((angle * MathUtils.degreesToRadians) - MathUtils.PI / 2)
    //textureMapObject.setRotation(angle - (MathUtils.degreesToRadians * 90))
    textureMapObject.setRotation(angle)
    textureMapObject.setName(name)
    textureMapObject.setX(spawnX)
    textureMapObject.setY(spawnY)

    objectRenderingServiceHandler.addGameObject(tiledMapTileMapObject)
    projectileMoveCache.add(ProjectileMoveRequest(name,SPEED,angle))

    ghostFireballCount += 1
  }

  private def setTexturePositionToWorldGridSystem(textureMapObject: TextureMapObject): Unit = {
    val x = textureMapObject.getX
    val y = textureMapObject.getY
    val screenPosition = Vector3(x, y, 0)
    val worldPosition = tiledMapConfig.viewport.getCamera.unproject(screenPosition)

    textureMapObject.setX(worldPosition.x)
    textureMapObject.setY(worldPosition.y)
  }

  private def calculateAngle(x1: Float, y1: Float, x2: Float, y2: Float): Float = MathUtils.atan2(y2 - y1, x2 - x1)

  private def getMouseCoordsInWorld: Vector2 = tiledMapConfig.viewport.unproject(Vector2(Gdx.input.getX.toFloat, Gdx.input.getY.toFloat))

}
