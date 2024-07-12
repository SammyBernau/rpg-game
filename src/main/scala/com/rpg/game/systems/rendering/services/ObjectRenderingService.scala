package com.rpg.game.systems.rendering.services

import com.badlogic.gdx.maps.objects.TextureMapObject
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer

import javax.inject.Inject


/**
 * Extends base class of OrthogonalTiledMapRenderer to provide support for rendering objects from the object layer of a Tiled map
 *
 * @param map -> Tiled map
 * @author Sam Bernau
 */

//TODO -> probably still need a map to track which objects to remove versus a map
class ObjectRenderingService @Inject(gameObjectCache: GameObjectCache, map: TiledMap) extends OrthogonalTiledMapRenderer(map) {
  
  /**
   * Renders textures at the locations of their respective physic objects. Handles both preloaded textures and dynamically added ones
   */
  protected override def renderObject(): Unit = {
    gameObjectCache.getCache.foreach {element =>
      updateTextureToObject(element._2)
    }
  }

  /**
   * Updates a texture to the location of their respective physics object
   */
  private def updateTextureToObject(gameObject: GameObject): Unit = {
    val mapObject = gameObject.mapObject
    mapObject match {
      case textureMapObject: TextureMapObject =>
        val fixture = gameObject.fixture

        val width = textureMapObject.getTextureRegion.getRegionWidth
        val height = textureMapObject.getTextureRegion.getRegionHeight

        val textureOriginX = textureMapObject.getOriginX
        val textureOriginY = textureMapObject.getOriginY
        //TODO -> collision boxes still not being drawn correctly
        val desiredX = (fixture.getBody.getTransform.getPosition.x - textureOriginX) - (width / 2f)
        val desiredY = (fixture.getBody.getTransform.getPosition.y - textureOriginY) - (height / 1.9f)
        //        val desiredX = fixture.getBody.getTransform.getPosition.x - (width / 2f)
        //        val desiredY = fixture.getBody.getTransform.getPosition.y - (height / 2f)

        if (textureMapObject.getX != desiredX) {
          textureMapObject.setX(desiredX)
        }
        if (textureMapObject.getY != desiredY) {
          textureMapObject.setY(desiredY)
        }

        batch.draw(textureMapObject.getTextureRegion, textureMapObject.getX, textureMapObject.getY, textureMapObject.getOriginX,
          textureMapObject.getOriginY, textureMapObject.getTextureRegion.getRegionWidth.toFloat, textureMapObject.getTextureRegion.getRegionHeight.toFloat,
          textureMapObject.getScaleX, textureMapObject.getScaleY, textureMapObject.getRotation)
      case _ =>
      //Not updating needed if a Fixture has no relative TextureMapObject
    }

  }


}

