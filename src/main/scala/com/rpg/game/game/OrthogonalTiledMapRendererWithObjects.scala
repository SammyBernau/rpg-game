package com.rpg.game.game

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.maps.objects.{RectangleMapObject, TextureMapObject}
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.physics.box2d.{BodyDef, Fixture, FixtureDef, PolygonShape, Shape}
import com.rpg.game.game.config.GameConfig.GameWorld.WORLD


/**
 * Extends base class of OrthogonalTiledMapRenderer to provide support for rendering objects from the object layer of a Tiled map
 * @param map -> Tiled map
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
        batch.draw(textureObj.getTextureRegion, textureObj.getX, textureObj.getY,
          textureObj.getOriginX, textureObj.getOriginY, textureObj.getTextureRegion.getRegionWidth.toFloat, textureObj.getTextureRegion.getRegionHeight.toFloat,
          textureObj.getScaleX, textureObj.getScaleY, textureObj.getRotation)
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
  private def addFixture(name: String, fixture: Fixture): Unit = {
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
   * This method was completed with the help from https://lyze.dev/2021/03/25/libGDX-Tiled-Box2D-example/
   */
  def parseDynamicObjectsFromMap(): Unit = {
    val objs = map.getLayers.get("entity").getObjects

    for (i <- 0 until objs.getCount) {
      val obj = objs.get(i)

      obj match {
        case rectangleMapObject: RectangleMapObject =>
          val rectangle = rectangleMapObject.getRectangle
          val width = rectangle.getWidth / 2f
          val height = rectangle.getHeight / 2f
          val bodyDef = getBodyDef(rectangle.getX + width, rectangle.getY + height, BodyType.StaticBody)

          val body = WORLD.createBody(bodyDef)

          val polygonShape = new PolygonShape()
          polygonShape.setAsBox(width, height)

          val fixtureDef = getDynamicBodyFixture(polygonShape)
          val fixture = body.createFixture(fixtureDef)
          addFixture(rectangleMapObject.getName,fixture)
          polygonShape.dispose()


        case textureMapObj: TextureMapObject =>
          val textureRegion = textureMapObj.getTextureRegion
          val width = textureRegion.getRegionWidth / 2f
          val height = textureRegion.getRegionHeight / 2f
          val bodyDef = getBodyDef(textureMapObj.getX + width, textureMapObj.getY + height, BodyType.DynamicBody)


          println(s"Dimensions for obj box ${textureMapObj.getName}: width ->${width}, height ->${height}")

          val body = WORLD.createBody(bodyDef)
          val polygonShape = new PolygonShape()
          polygonShape.setAsBox(width, height)
          val fixtureDef = getDynamicBodyFixture(polygonShape)
          val fixture = body.createFixture(fixtureDef)
          addFixture(textureMapObj.getName,fixture)
          polygonShape.dispose()

        case _ =>
          println(s"Tried printing ${obj.getName} with properties: ${obj.getProperties}")
      }
    }
  }

  /**
   * Defines a base body for all dynamic objects
   * @param x
   * @param y
   * @param bodyType
   * @return
   */
  private def getBodyDef(x: Float, y: Float, bodyType: BodyType): BodyDef = {
    val bodyDef = new BodyDef
    bodyDef.`type` = bodyType
    bodyDef.position.set(x, y)
    bodyDef.linearDamping = 1f
    bodyDef.fixedRotation = true

    bodyDef
  }

  /**
   * Defines a base fixture for all dynamic objects
   * @param shape
   * @return
   */
  private def getDynamicBodyFixture(shape: Shape): FixtureDef = {
    val fixtureDef = new FixtureDef
    fixtureDef.shape = shape
    fixtureDef.density = 0.5f
    fixtureDef.friction = 1f
    fixtureDef.restitution = 0.0f

    fixtureDef
  }

}

