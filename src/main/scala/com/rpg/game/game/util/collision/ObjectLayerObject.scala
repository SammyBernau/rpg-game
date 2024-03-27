package com.rpg.game.game.util.collision

import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.maps.objects.{EllipseMapObject, PolygonMapObject, PolylineMapObject, RectangleMapObject, TextureMapObject}
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
  val fixture: Fixture = createFixture()

  /**
   * Returns a fixture based on object settings
   * Currently no legitimate support for kinematic body types
   * @return
   */
  private def createFixture(): Fixture = {
    val bodyType = stringToBodyType(mapObject.getProperties.get("BodyType").toString)

    val fixture = mapObject match {

      case rectangleMapObject: RectangleMapObject =>
        val rectangle = rectangleMapObject.getRectangle
        val width = rectangle.getWidth / 2f
        val height = rectangle.getHeight / 2f

        val body = getBody(rectangle.getX + width,rectangle.getY + height,bodyType)

        val polygonShape = new PolygonShape()
        polygonShape.setAsBox(width,height)


        defineFixture(body,polygonShape,bodyType)
      case textureMapObj: TextureMapObject =>
        val textureRegion = textureMapObj.getTextureRegion
        val width = textureRegion.getRegionWidth /2f
        val height = textureRegion.getRegionHeight /2f

        val body = getBody(textureMapObj.getX + width,textureMapObj.getY + height,bodyType)

        val polygonShape = new PolygonShape()
        polygonShape.setAsBox(textureRegion.getRegionWidth.toFloat/2f,textureRegion.getRegionHeight.toFloat/2f)


        defineFixture(body,polygonShape,bodyType)
      case polygonMapObj: PolygonMapObject =>
        val polygon = polygonMapObj.getPolygon
        //val width,height = getPolygonShapeDimensions(polygon)

        val bodyType = stringToBodyType(polygonMapObj.getProperties.get("BodyType").toString)
        val body = getBody(polygon.getX,polygon.getY,bodyType)

        val polygonShape = new PolygonShape()
        polygonShape.set(polygon.getVertices)


        defineFixture(body,polygonShape,bodyType)

      case ellipseMapObject: EllipseMapObject => //ellipse = circle
        val ellipse = ellipseMapObject.getEllipse
        val width = ellipse.width / 2
        val height = ellipse.height / 2
        val bodyType = stringToBodyType(ellipseMapObject.getProperties.get("BodyType").toString)
        val body = getBody(ellipse.x + width,ellipse.y + height,bodyType)

        val circleShape = new CircleShape()
        circleShape.setRadius(ellipse.width/2f)

        defineFixture(body,circleShape,bodyType)
      case polyLineMapObject: PolylineMapObject =>
        val polyLine = polyLineMapObject.getPolyline
        val bodyType = stringToBodyType(polyLineMapObject.getProperties.get("BodyType").toString)
        val body = getBody(polyLine.getX,polyLine.getY,bodyType)

        val polyLineShape = new ChainShape()
        polyLineShape.createChain(polyLine.getVertices)

        defineFixture(body,polyLineShape,bodyType)

      case _ =>
        throw new IllegalStateException(s"No matching ShapeMapObject found for ${mapObject} with properties: ${mapObject.getProperties}")
    }

    fixture
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
