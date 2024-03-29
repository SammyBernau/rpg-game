package com.rpg.game.game.util.collision

import com.badlogic.gdx.graphics.g3d.utils.shapebuilders.EllipseShapeBuilder
import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.maps.objects.{EllipseMapObject, PolygonMapObject, PolylineMapObject, RectangleMapObject, TextureMapObject}
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject
import com.badlogic.gdx.math.{Polygon, Vector2}
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.rpg.game.game.config.CurrentWorld
import com.rpg.game.game.config.GameConfig.GameWorld.WORLD


/**
 * ObjectLayerObject is defined as all objects in the object layer of a tiled map layer.
 * Works for both tiles inserted as objects and blank shapes drawn around tiles.
 * @param mapObject -> current mapobject
 * @author Sam Bernau
 */
class ObjectLayerObject(mapObject: MapObject) {
  val fixture: Fixture = buildFixture()

  /**
   * Returns a fixture based on object settings
   * Currently no legitimate support for kinematic body types
   * @return
   */
  private def buildFixture(): Fixture = {

  val bodyType = try {
    stringToBodyType(mapObject.getProperties.get("BodyType").toString)
  } catch {
    case e: Exception =>
      println(e.getStackTrace.mkString("Array(", ", ", ")"))
      println(s"BodyType is either null or incorrectly set for this object: ${mapObject.getName}")
      BodyType.StaticBody
  }
    mapObject match {

      case rectangleMapObject: RectangleMapObject =>
        fixtureFromRectangle(bodyType, rectangleMapObject,rectangleMapObject.getRectangle.getX, rectangleMapObject.getRectangle.getY)

      case polygonMapObj: PolygonMapObject =>
        fixtureFromPolygon(bodyType, polygonMapObj, polygonMapObj.getPolygon.getX,polygonMapObj.getPolygon.getY)

      case ellipseMapObject: EllipseMapObject => //ellipse = circle
        fixtureFromEllipse(bodyType, ellipseMapObject, ellipseMapObject.getEllipse.x,ellipseMapObject.getEllipse.y)
      case polyLineMapObject: PolylineMapObject =>
        fixtureFromPolyline(bodyType, polyLineMapObject, polyLineMapObject.getPolyline.getX, polyLineMapObject.getPolyline.getY)

      case textureMapObj: TextureMapObject => //TextureMapObjects are Tiles inserted as objects into object layer
        val tileObject = mapObject.asInstanceOf[TiledMapTileMapObject]
        val tile = tileObject.getTile
        val boundingBox = tile.getObjects.get(0)

        boundingBox match {
          case rectangleMapObject: RectangleMapObject =>
            fixtureFromRectangle(bodyType, rectangleMapObject,textureMapObj.getX, textureMapObj.getY)

          case ellipseMapObject: EllipseMapObject =>
            fixtureFromEllipse(bodyType, ellipseMapObject, textureMapObj.getX,textureMapObj.getY)

          case polylineMapObject: PolylineMapObject =>
            val polyLine = polylineMapObject.getPolyline
            val body = getBody(textureMapObj.getX, textureMapObj.getY, bodyType)

            val vertices = polyLine.getTransformedVertices
            val width = polyLine.getBoundingRectangle.getWidth
            val height = polyLine.getBoundingRectangle.getHeight
            val adjustedVertices = new Array[Float](polyLine.getVertices.length)

            for(i <- vertices.indices by 2) {
              adjustedVertices(i) = vertices(i) - (width/2f)
              adjustedVertices(i+1) = vertices(i+1) - (height/2f)
            }

            val polyLineShape = new ChainShape()
            polyLineShape.createChain(adjustedVertices)

            defineFixture(body, polyLineShape, bodyType)
          case _ =>
            throw new IllegalStateException(s"No matching bounding_box found for ${tileObject.getName} with properties: ${tileObject.getProperties}")

        }

      case _ =>
        throw new IllegalStateException(s"No matching ShapeMapObject found for ${mapObject.getName} with properties: ${mapObject.getProperties}")
    }

  }

  /**
   * Returns fixture based on polyline object
   * @param bodyType BodyType of object
   * @param polyLineMapObject -> object parsed from TiledMap
   * @param x -> x coord
   * @param y -> y coord
   * @return
   */
  private def fixtureFromPolyline(bodyType: BodyType, polyLineMapObject: PolylineMapObject, x: Float, y: Float): Fixture = {
    val polyLine = polyLineMapObject.getPolyline
    val body = getBody(x, y, bodyType)

    val polyLineShape = new ChainShape()
    polyLineShape.createChain(polyLine.getTransformedVertices)

    defineFixture(body, polyLineShape, bodyType)
  }

  private def fixtureFromEllipse(bodyType: BodyType, ellipseMapObject: EllipseMapObject, x: Float, y: Float): Fixture = {
    val ellipse = ellipseMapObject.getEllipse
    val width = ellipse.width / 2f
    val height = ellipse.height / 2f
    val body = getBody(x + width, y + height, bodyType)

    val circleShape = new CircleShape()
    circleShape.setRadius(width)

    defineFixture(body, circleShape, bodyType)
  }

  private def fixtureFromPolygon(bodyType: BodyType, polygonMapObj: PolygonMapObject, x: Float, y: Float): Fixture = {
    val polygon = polygonMapObj.getPolygon

    val body = getBody(x, y, bodyType)

    val polygonShape = new PolygonShape()
    polygonShape.set(polygon.getVertices)

    defineFixture(body, polygonShape, bodyType)
  }

  private def fixtureFromRectangle(bodyType: BodyType, rectangleMapObject: RectangleMapObject, x: Float, y: Float): Fixture = {
    val rectangle = rectangleMapObject.getRectangle
    val width = rectangle.getWidth / 2f
    val height = rectangle.getHeight / 2f

    val body = getBody(x + width, y + height, bodyType)


    val polygonShape = new PolygonShape()
    polygonShape.setAsBox(width, height)

    defineFixture(body, polygonShape, bodyType)
  }


  private def getPolygonShapeDimensions(poly: Polygon): (Float, Float) = {


    val vertices = Array.fill(poly.getVertexCount)(new Vector2())

    for (i <- 0 until poly.getVertexCount) {
      poly.getVertex(i, vertices(i))
    }

    val minX = vertices.minBy(_.x).x
    val maxX = vertices.maxBy(_.x).x
    val minY = vertices.minBy(_.y).y
    val maxY = vertices.maxBy(_.y).y

    val width = maxX - minX
    val height = maxY - minY

    (width, height)
  }


  /**
   * Defines a fixture based if its dynamic or static
   * @param body -> body of object
   * @param shape -> shape of object
   * @param bodyType -> BodyType of object
   * @return
   */
  private def defineFixture(body: Body,shape: Shape, bodyType: BodyType): Fixture = {

    if (bodyType == BodyType.DynamicBody) {
      val fixtureDef: FixtureDef = getDynamicBodyFixtureDef(shape)
      body.createFixture(fixtureDef)
    }

    body.createFixture(shape, 0.0f)
  }

  def stringToBodyType(bodyTypeAsString: String): BodyType = {
    bodyTypeAsString match {
      case "Dynamic" => BodyType.DynamicBody
      case "dynamic" => BodyType.DynamicBody
      case "DynamicBody" => BodyType.DynamicBody
      case "dynamicBody" => BodyType.DynamicBody

      case "Static" => BodyType.StaticBody
      case "static" => BodyType.StaticBody
      case "StaticBody" => BodyType.StaticBody
      case "staticBody" => BodyType.StaticBody

      case "Kinematic" => BodyType.KinematicBody
      case "kinematic" => BodyType.KinematicBody
      case "KinematicBody" => BodyType.KinematicBody
      case "kinematicBody" => BodyType.KinematicBody

      case _ => throw new IllegalArgumentException("Invalid BodyType or BodyType set incorrectly in Tiled object properties")
    }
  }



  /**
   * Returns a base fixture
   *
   * @param shape -> shape created from object dimensions
   * @return
   */
  private def getDynamicBodyFixtureDef(shape: Shape): FixtureDef = {
    val fixtureDef = new FixtureDef
    fixtureDef.shape = shape
    fixtureDef.density = 0.5f
    fixtureDef.friction = 1f
    fixtureDef.restitution = 0.0f

    fixtureDef
  }

  /**
   * Returns a base body
   * @param x -> x coord of body
   * @param y -> y coord of body
   * @param bodyType -> BodyType defined by object in object layer
   * @return
   */
  private def getBody(x: Float, y: Float, bodyType: BodyType): Body = {
    val bodyDef = new BodyDef
    bodyDef.position.set(x, y)
    bodyDef.`type` = bodyType
    if(bodyType == BodyType.DynamicBody) {
      bodyDef.linearDamping = 1f
      bodyDef.fixedRotation = true
    }
    WORLD.createBody(bodyDef)
  }

}
