package com.rpg.game.entity.item.equipment.projectile

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.maps.objects.{RectangleMapObject, TextureMapObject}
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile
import com.badlogic.gdx.math.Vector3
import com.rpg.game.entity.textures.EntityAnimations
import com.rpg.game.game.config.CurrentWorld
import com.rpg.game.game.util.collision.ObjectLayerObject

class GhostFireball(currentWorld: CurrentWorld, batch: SpriteBatch) {

  private val playerFixture = currentWorld.mapRenderer.getFixture("player_animation")
  private val entityAnimations = EntityAnimations(currentWorld)
  private val ghostFireballTile = entityAnimations.GhostFireBall.tile
  private val ghostFireballTexture = entityAnimations.GhostFireBall.textureRegion

  def shoot(): Unit = {
    val playerPosition = playerFixture.getBody.getPosition
    val playerX = playerPosition.x
    val playerY = playerPosition.y
    val rectangleMapObject = ghostFireballTile.getObjects.get(0).asInstanceOf[RectangleMapObject]
    rectangleMapObject.getRectangle.x = playerX
    rectangleMapObject.getRectangle.y = playerY

    val obj = new TiledMapTileMapObject(ghostFireballTile, false, false)
    obj.setName(ghostFireballTile.getProperties.get("type").toString)
    obj.setX(playerX)
    obj.setY(playerY)
    //TODO -> not working (fireball image drawn at wrong coords somehow) and fireball fixture is not being created (missing BodyType flag)
    currentWorld.mapRenderer.addObject(rectangleMapObject)

    val textureMapObject = obj.asInstanceOf[TextureMapObject]

    currentWorld.mapRenderer.addTextureMapObject(textureMapObject.getName,textureMapObject)



    batch.begin()
    batch.draw(textureMapObject.getTextureRegion,
      textureMapObject.getX,
      textureMapObject.getY,
      textureMapObject.getOriginX, textureMapObject.getOriginY,
      textureMapObject.getTextureRegion.getRegionWidth.toFloat, textureMapObject.getTextureRegion.getRegionHeight.toFloat,
      textureMapObject.getScaleX, textureMapObject.getScaleY,
      textureMapObject.getRotation)
    currentWorld.mapRenderer.updateTextureToObject(textureMapObject.getName)
    batch.end()

  }

}
