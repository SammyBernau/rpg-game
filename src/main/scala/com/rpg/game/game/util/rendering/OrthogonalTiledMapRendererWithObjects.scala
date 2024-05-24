package com.rpg.game.game.util.rendering

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.maps.objects.{RectangleMapObject, TextureMapObject}
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.physics.box2d.*
import com.rpg.game.game.config.GameConfig.GameWorld.WORLD
import com.rpg.game.game.util.collision.ObjectLayerObject


/**
 * Extends base class of OrthogonalTiledMapRenderer to provide support for rendering objects from the object layer of a Tiled map
 *
 * @param map -> Tiled map
 * @author Sam Bernau
 */
class OrthogonalTiledMapRendererWithObjects(map: TiledMap) extends OrthogonalTiledMapRenderer(map) {

  private var textureObjects: Map[String, TextureMapObject] = Map() //stores textures of physics objects
  private var fixtures: Map[String, Fixture] = Map() //stores physics objects

  /**
   * Renders textures at the locations of their respective physic objects
   * @param obj -> objects found in the object layer of Tiled map
   */
  override def renderObject(obj: MapObject): Unit = {
    obj match {
      case textureObj: TextureMapObject =>
        addTextureMapObject(textureObj.getName,textureObj)
        batch.draw(textureObj.getTextureRegion, textureObj.getX, textureObj.getY, textureObj.getOriginX, textureObj.getOriginY, textureObj.getTextureRegion.getRegionWidth.toFloat, textureObj.getTextureRegion.getRegionHeight.toFloat, textureObj.getScaleX, textureObj.getScaleY, textureObj.getRotation)
        updateTextureToObject(textureObj.getName)
      case _ =>
        //TODO -> fill in base case
    }
  }

  /**
   *
   * @param name -> name of entity taken from Tiled map
   * @param textureMapObject -> texture defined in Tiled map
   */
  private def addTextureMapObject(name: String, textureMapObject: TextureMapObject): Unit = {
    textureObjects = textureObjects + (name -> textureMapObject)
  }

  /**
   *
   * @param name -> name of entity taken from Tiled map
   * @return
   */
  def getTextureMapObject(name: String): TextureMapObject = {
    textureObjects.getOrElse(name, throw new NoSuchElementException("No TextureMapObject found with in map with key: " + name))
  }

  /**
   *
   * @param name -> name of entity taken from Tiled map
   * @param fixture -> fixture defined from object
   */
  def addFixture(name: String, fixture: Fixture): Unit = {
    fixtures = fixtures + (name -> fixture)
  }

  /**
   *
   * @param name -> name of entity taken from Tiled map
   * @return
   */
  def getFixture(name: String): Fixture = {
    fixtures.getOrElse(name, throw new NoSuchElementException("No Fixture found with in map with key: " + name))
  }


  /**
   * This method was completed with help from https://lyze.dev/2021/03/25/libGDX-Tiled-Box2D-example/
   */
  def parseObjectsFromMap(): Unit = {
    val objs = map.getLayers.get("entity").getObjects

    for (i <- 0 until objs.getCount) {
      val obj = objs.get(i)

      val objectFixture = new ObjectLayerObject(obj)
      addFixture(obj.getName,objectFixture.fixture)

    }
  }


  /**
   * Updates a texture to the location of their respective physic object
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
      val desiredX = (fixture.getBody.getTransform.getPosition.x - textureOriginX) - (width/2f)
      val desiredY = (fixture.getBody.getTransform.getPosition.y - textureOriginY) - (height/1.9f)
//        val desiredX = fixture.getBody.getTransform.getPosition.x - (width / 2f)
//        val desiredY = fixture.getBody.getTransform.getPosition.y - (height / 2f)

      if (texture.getX != desiredX) {
        texture.setX(desiredX)
      }
      if(texture.getY != desiredY) {
        texture.setY(desiredY)
      }
  }





}

