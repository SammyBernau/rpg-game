package com.rpg.game.entity.item.equipment.projectile

import com.badlogic.gdx.{Gdx, Input}
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.maps.objects.{RectangleMapObject, TextureMapObject}
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile
import com.badlogic.gdx.math.{MathUtils, Vector2, Vector3}
import com.rpg.game.entity.textures.EntityAnimations
import com.rpg.game.game.config.CurrentWorld
import com.rpg.game.game.util.collision.ObjectLayerObject
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

      val spawnX = playerX + (MathUtils.cos(angle) * spawnDistance)
      println(s"spawnX: ${spawnX}")
      val spawnY = playerY + (MathUtils.sin(angle) * spawnDistance)
      println(s"spawnY: ${spawnY}")

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

      addProjectile(obj)
      currentWorld.mapRenderer.addNewObjWithTexture(textureMapObject, rectangleMapObject)
      entityLayer.getObjects.add(textureMapObject) //adds sprite to entity layer to be drawn

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
  private def getMouseCoordsInWorld: Vector3 = currentWorld.viewport.getCamera.unproject(Vector3(Gdx.input.getX.toFloat, Gdx.input.getY.toFloat, 0))



  //DONT NEED THIS DRAW METHOD SINCE OrthogonalTiledMapRendererWithObjects handles all the draw calls and updates
  //  override def draw(batch: SpriteBatch): Unit = {
  //    if(getProjectiles.nonEmpty) {
  //      batch.begin()
  //      getProjectiles.foreach { obj =>
  //        val textureMapObject = obj.asInstanceOf[TextureMapObject]
  //        val x = textureMapObject.getX
  //        val y = textureMapObject.getY
  //        println(s"GhostFireballTexture Position for ${obj.getName}: ${x},${y}")
  //        //batch.draw(textureMapObject.getTextureRegion, x, y)
  ////        batch.draw(textureMapObject.getTextureRegion, textureMapObject.getX, textureMapObject.getY, textureMapObject.getOriginX,
  ////          textureMapObject.getOriginY, textureMapObject.getTextureRegion.getRegionWidth.toFloat, textureMapObject.getTextureRegion.getRegionHeight.toFloat,
  ////          textureMapObject.getScaleX, textureMapObject.getScaleY, textureMapObject.getRotation)
  //        //currentWorld.mapRenderer.updateTextureToObject(obj.getName)
  //      }
  //      batch.end()
  //    }
  //  }


}
