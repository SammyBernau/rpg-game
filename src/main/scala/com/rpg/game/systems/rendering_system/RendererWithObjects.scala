package com.rpg.game.systems.rendering_system

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.maps.objects.{RectangleMapObject, TextureMapObject}
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.physics.box2d.*
import com.rpg.entity.ObjectUserData
import com.rpg.game.systems.physics_system.ObjectLayerObject


/**
 * Extends base class of OrthogonalTiledMapRenderer to provide support for rendering objects from the object layer of a Tiled map
 *
 * @param map -> Tiled map
 * @author Sam Bernau
 */
class RendererWithObjects(map: TiledMap) extends OrthogonalTiledMapRenderer(map) {

  private val entityLayer = map.getLayers.get("entity").getObjects
  private var textureObjects: Map[String, TextureMapObject] = Map() //stores textures of physics objects
  private var fixtures: Map[String, Fixture] = Map() //stores physics objects
  
  /**
   * Renders textures at the locations of their respective physic objects. Handles both preloaded textures and dynamically added ones
   *
   * @param obj -> objects found in the object layer of Tiled map
   */
  override def renderObject(obj: MapObject): Unit = {
    obj match {
      case textureMapObject: TextureMapObject =>
        val name = textureMapObject.getName
        addTextureMapObject(name, textureMapObject)
        updateTextureToObject(name)
        batch.draw(textureMapObject.getTextureRegion, textureMapObject.getX, textureMapObject.getY, textureMapObject.getOriginX,
          textureMapObject.getOriginY, textureMapObject.getTextureRegion.getRegionWidth.toFloat, textureMapObject.getTextureRegion.getRegionHeight.toFloat,
          textureMapObject.getScaleX, textureMapObject.getScaleY, textureMapObject.getRotation)
      case _ =>
      //TODO -> fill in base case
    }
  }

  /**
   *
   * @param name             -> name of entity taken from Tiled map
   * @param textureMapObject -> texture defined in Tiled map
   */
  private def addTextureMapObject(name: String, textureMapObject: TextureMapObject): Unit = textureObjects = textureObjects + (name -> textureMapObject)

  /**
   *
   * @param name -> name of entity taken from Tiled map
   * @return
   */
  def getTextureMapObject(name: String): TextureMapObject = textureObjects.getOrElse(name, throw new NoSuchElementException("No TextureMapObject found with in map with key: " + name))

  /**
   * @param name    -> name of entity taken from Tiled map
   * @param fixture -> fixture defined from object
   */
  private def addFixture(name: String, fixture: Fixture): Unit = fixtures = fixtures + (name -> fixture)

  /**
   *
   * @param name -> name of entity taken from Tiled map
   * @return
   */
  def getFixture(name: String): Fixture = fixtures.getOrElse(name, throw new NoSuchElementException("No Fixture found with in map with key: " + name))

  /**
   * This method was completed with help from https://lyze.dev/2021/03/25/libGDX-Tiled-Box2D-example/
   * Current implementation only parses objects that were pre-loaded on map
   */
  def parseObjectsFromMap(): Unit = {

    for (i <- 0 until entityLayer.getCount) {
      val obj = entityLayer.get(i)
      addObject(obj)
    }
  }

  //Really it creates a new object
  private def addObject(obj: MapObject): Unit = {
    val objectFixture = new ObjectLayerObject(obj)
    addFixture(obj.getName, objectFixture.fixture)
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
    //Create collision box for new object
    addObject(obj)

    //load texture into texture map
    addTextureMapObject(texture.getName, texture)
  }

  /**
   * Updates a texture to the location of their respective physic object
   *
   * @param name -> name of texture/fixture stored in maps
   */
  private def updateTextureToObject(name: String): Unit = {

    val texture = getTextureMapObject(name)
    val fixture = getFixture(name)

      val width = texture.getTextureRegion.getRegionWidth
      val height = texture.getTextureRegion.getRegionHeight

      val textureOriginX = texture.getOriginX
      val textureOriginY = texture.getOriginY
      //TODO -> collision boxes still not being drawn correctly
      val desiredX = (fixture.getBody.getTransform.getPosition.x - textureOriginX) - (width / 2f)
      val desiredY = (fixture.getBody.getTransform.getPosition.y - textureOriginY) - (height / 1.9f)
      //        val desiredX = fixture.getBody.getTransform.getPosition.x - (width / 2f)
      //        val desiredY = fixture.getBody.getTransform.getPosition.y - (height / 2f)

      if (texture.getX != desiredX) {
        texture.setX(desiredX)
      }
      if (texture.getY != desiredY) {
        texture.setY(desiredY)
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

