package com.rpg.game.game.util

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.maps.objects.{RectangleMapObject, TextureMapObject}
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.physics.box2d.*
import com.rpg.game.game.config.GameConfig.GameWorld.WORLD


/**
 * Extends base class of OrthogonalTiledMapRenderer to provide support for rendering objects from the object layer of a Tiled map
 *
 * @param map -> Tiled map
 * @author Sam Bernau
 */
class OrthogonalTiledMapRendererWithObjects(map: TiledMap) extends OrthogonalTiledMapRenderer(map) {

  private var textureObjects: Map[String, TextureMapObject] = Map()
  private var fixtures: Map[String, Fixture] = Map()

  /**
   *
   * @param obj -> objects found in the object layer of Tiled map
   */
  override def renderObject(obj: MapObject): Unit = {
    obj match {
      case textureObj: TextureMapObject =>
        addTextureMapObject(textureObj.getName,textureObj)
        batch.draw(textureObj.getTextureRegion, textureObj.getX, textureObj.getY, textureObj.getOriginX, textureObj.getOriginY, textureObj.getTextureRegion.getRegionWidth.toFloat, textureObj.getTextureRegion.getRegionHeight.toFloat, textureObj.getScaleX, textureObj.getScaleY, textureObj.getRotation)
        updateTextureToObjects(textureObj.getName)
      case _ =>
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
  def parseDynamicObjectsFromMap(): Unit = {
    val objs = map.getLayers.get("entity").getObjects

    for (i <- 0 until objs.getCount) {
      val obj = objs.get(i)

      obj match {
        //All rectangles inserted into object layer parse as RectangleMapObjects
        case rectangleMapObject: RectangleMapObject =>
          val rectangle = rectangleMapObject.getRectangle
          if(rectangleMapObject.getProperties.get("BodyType") == "dynamic") {
            val fixture = ObjectLayerObject(rectangleMapObject.getName,rectangle.getX,rectangle.getY,rectangle.getWidth,rectangle.getHeight,BodyType.DynamicBody).fixture
            addFixture(rectangleMapObject.getName,fixture)
          } else if(rectangleMapObject.getProperties.get("BodyType") == "static") {
            val fixture = ObjectLayerObject(rectangleMapObject.getName,rectangle.getX,rectangle.getY,rectangle.getWidth,rectangle.getHeight, BodyType.StaticBody).fixture
            addFixture(rectangleMapObject.getName,fixture)
          }

          //All tiles inserted as objects into object layer parse as TextureMapObjects
        case textureMapObj: TextureMapObject =>
          val textureRegion = textureMapObj.getTextureRegion
          if(textureMapObj.getProperties.get("BodyType") == "dynamic") {
            val fixture = ObjectLayerObject(textureMapObj.getName,
              textureMapObj.getX,textureMapObj.getY,
              textureRegion.getRegionWidth.toFloat,textureRegion.getRegionHeight.toFloat,
              BodyType.DynamicBody).fixture
            addFixture(textureMapObj.getName, fixture)
          } else if (textureMapObj.getProperties.get("BodyType") == "static") {
            val fixture = ObjectLayerObject(textureMapObj.getName,textureMapObj.getX, textureMapObj.getY,textureRegion.getRegionWidth.toFloat, textureRegion.getRegionHeight.toFloat, BodyType.StaticBody).fixture
            addFixture(textureMapObj.getName,fixture)
          }
        case _ =>
          println(s"Tried printing ${obj.getName} with properties: ${obj.getProperties}")
      }
    }
  }


  /**
   * Updates all textures to follow their respective collision boxes
   * @param name -> name of texture/fixture stores in maps
   */
  private def updateTextureToObjects(name: String): Unit = {

      val texture = getTextureMapObject(name)
      val fixture = getFixture(name)

      val width = texture.getTextureRegion.getRegionWidth
      val height = texture.getTextureRegion.getRegionHeight

      val textureOriginX = texture.getOriginX
      val textureOriginY = texture.getOriginY

      val desiredX = (fixture.getBody.getTransform.getPosition.x - textureOriginX) - (width / 2)
      val desiredY = (fixture.getBody.getTransform.getPosition.y - textureOriginY) - (height / 2)

      if (texture.getX != desiredX) {
        texture.setX(desiredX)
      }
      if(texture.getY != desiredY) {
        texture.setY(desiredY)
      }
  }





}

