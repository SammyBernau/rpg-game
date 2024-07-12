package com.rpg.game.systems.rendering

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.maps.objects.{RectangleMapObject, TextureMapObject}
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.rpg.entity.ObjectUserData
import com.rpg.game.systems.physics.world.PhysicsObjectProducer

import javax.inject.Inject
import scala.language.postfixOps


/**
 * Extends base class of OrthogonalTiledMapRenderer to provide support for rendering objects from the object layer of a Tiled map
 *
 * @param map -> Tiled map
 * @author Sam Bernau
 */

//TODO -> probably still need a map to track which objects to remove versus a map
class ObjectRenderingService @Inject(gameObjectCache: GameObjectCache, map: TiledMap) extends OrthogonalTiledMapRenderer(map) {

  private val entityLayer = map.getLayers.get("entity").getObjects
  
  /**
   * Renders textures at the locations of their respective physic objects. Handles both preloaded textures and dynamically added ones
   *
   * @param obj -> objects found in the object layer of Tiled map
   */
  override def renderObject(): Unit = {
    gameObjectCache.getCache.foreach{element =>
      updateTextureToObject(element)
    }
  }
  
  /**
   * This method was completed with help from https://lyze.dev/2021/03/25/libGDX-Tiled-Box2D-example/
   * Current implementation only parses objects that were pre-loaded on map
   */
  def parseObjectsFromMap(): Unit = {

    for (i <- 0 until entityLayer.getCount) {
      val obj = entityLayer.get(i)
      addGameObject(obj)
    }
  }

  //creates a new object
  private def addGameObject(obj: MapObject): Unit = {
    
  }

  /**
   * Dynamically added objects need to have their texture added to the entity layer in order to be rendered
   *
   * @param textureMapObject
   */
  def addToObjectLayer(textureMapObject: TextureMapObject): Unit = entityLayer.add(textureMapObject)

  /**
   * Adds a new obj with its texture to the screen
   * This is used when you want to spawn a new object that was preloaded with the map, ie: bullets, enemies, etc.
   *
   * @param texture
   * @param obj
   */
  def addNewObjWithTexture(texture: TextureMapObject, obj: MapObject): Unit = {
//    //Create collision box for new object
//    addObject(obj)
//
//    //load texture into texture map
//    addTextureMapObject(texture.getName, texture)


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


  /**
   * Removes texture and fixture from above lists so they dont get rendered
   * Note: Removing the fixture from the fixture list doesn't delete the fixture, just decouples it from its respective texture
   * @param name -> name as map key
   */
   def removeTexture(name: String): Unit = {
    val texture = getTextureMapObject(name)
    val fixture = getFixture(name)
    val userData = fixture.getBody.getUserData.asInstanceOf[ObjectUserData]
    if (userData != null && userData.isFlaggedForDelete) {
      entityLayer.remove(texture)
      textureObjects -= name
      fixtures -= name
    }
  }


}

