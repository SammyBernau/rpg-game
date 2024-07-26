package com.rpg.game.systems.rendering.services.gameobjects

import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.maps.objects.TextureMapObject
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.math.MathUtils

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
  //TODO -> dont have to do for loop, just grab name from object and find in map and update. This renderObject method already grabs all objects on tiled map
  override def renderObject(mapObject: MapObject): Unit = synchronized {
    mapObject match {
      case textureMapObject: TextureMapObject =>
        val mapObjectName = textureMapObject.getName
        updateTextureToObject(mapObjectName)
      case _ =>
      //Nothing to do
    }

  }

  /**
   * Updates a texture to the location of their respective physics object
   */
  private def updateTextureToObject(objectName: String): Unit = synchronized {
    gameObjectCache.get(objectName) match {
      case Some(gameObject) =>
        val mapObject = gameObject.mapObject
        val textureMapObject = gameObject.mapObject.asInstanceOf[TextureMapObject]

        //fixture
        val fixture = gameObject.fixture
        val body = fixture.getBody
        val bodyPosition = body.getTransform.getPosition //getTransform is used to get the position because if getPosition is called by itself, objects move very fast

        //texture
        val textureWidth = textureMapObject.getTextureRegion.getRegionWidth
        val textureHeight = textureMapObject.getTextureRegion.getRegionHeight
        textureMapObject.setOriginX(textureWidth/2f)
        textureMapObject.setOriginY(textureHeight/2f)
        
        //TODO -> collision boxes still not being drawn correctly. This is only happening with the player as its animation changes so it appears that the box is jiggling
        val desiredX = (bodyPosition.x - textureMapObject.getOriginX)
        val desiredY = (bodyPosition.y - textureMapObject.getOriginY)

        if (textureMapObject.getX != desiredX) {
          textureMapObject.setX(desiredX)
        }
        if (textureMapObject.getY != desiredY) {
          textureMapObject.setY(desiredY)
        }

        batch.draw(textureMapObject.getTextureRegion, textureMapObject.getX, textureMapObject.getY, textureMapObject.getOriginX,
          textureMapObject.getOriginY, textureMapObject.getTextureRegion.getRegionWidth.toFloat, textureMapObject.getTextureRegion.getRegionHeight.toFloat,
          textureMapObject.getScaleX, textureMapObject.getScaleY, textureMapObject.getRotation)
      case None =>
        //Projectiles are not always created or found within GameObjectCache before some render calls
        //Does not crash or prevent the projectile from being created but returns as a None prior to returning a GameObject above
        println(s"No GameObject found with name: ${objectName}")
    }

  }
}

