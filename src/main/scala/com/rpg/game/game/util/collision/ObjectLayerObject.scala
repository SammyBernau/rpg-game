package com.rpg.game.game.util.collision

import com.badlogic.gdx.graphics.g3d.utils.shapebuilders.EllipseShapeBuilder
import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.maps.objects.{EllipseMapObject, PolygonMapObject, PolylineMapObject, RectangleMapObject, TextureMapObject}
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject
import com.badlogic.gdx.math.{Polygon, Vector2}
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.rpg.game.game.config.GameConfig.GameWorld.WORLD
import com.rpg.game.game.util.rendering.fixture.{FixtureCreator, FixtureCreatorFactory}
import com.rpg.game.game.util.rendering.fixture.shapes.{EllipseObject, PolygonObject, PolylineObject, PolylineObjectBoundingBox, RectangleObject, TextureObject}


/**
 * An ObjectLayerObject is defined as an object in the object layer of a tiled map layer.
 * Works for both tiles inserted as objects and free drawn objects with no textures.
 * @param mapObject -> current mapobject
 * @author Sam Bernau
 */

//TODO -> add collision filtering
class ObjectLayerObject(mapObject: MapObject) {

  // Returns a fixture based on object settings
  // Currently no legitimate support for kinematic body types
  val fixtureCreator: FixtureCreator = FixtureCreatorFactory.getFixtureCreator(mapObject)
  val fixture: Fixture = buildFixture()

  private def buildFixture(): Fixture = {
  val bodyType = try {
    stringToBodyType(mapObject.getProperties.get("BodyType").toString)
  } catch {
    case e: Exception =>
      println(e.getStackTrace.mkString("Array(", ", ", ")"))
      println(s"BodyType is either null or incorrectly set for this object: ${mapObject.getName}")
      println("...Setting BodyType to default: Static")
      BodyType.StaticBody
  }
    mapObject match {
      //---------Objects without textures---------//
      case rectangleMapObject: RectangleMapObject =>
        RectangleObject().getFixture(bodyType, rectangleMapObject,rectangleMapObject.getRectangle.getX, rectangleMapObject.getRectangle.getY)

      case polygonMapObj: PolygonMapObject =>
        PolygonObject().getFixture(bodyType, polygonMapObj, polygonMapObj.getPolygon.getX,polygonMapObj.getPolygon.getY)

      case ellipseMapObject: EllipseMapObject =>
        EllipseObject().getFixture(bodyType, ellipseMapObject, ellipseMapObject.getEllipse.x,ellipseMapObject.getEllipse.y)

      case polyLineMapObject: PolylineMapObject =>
        PolylineObject().getFixture(bodyType, polyLineMapObject, polyLineMapObject.getPolyline.getX, polyLineMapObject.getPolyline.getY)

      //---------Objects with textures that need to be created---------//
      case textureMapObj: TextureMapObject => //TextureMapObjects are Tiles inserted as objects into object layer
        TextureObject().getFixture(bodyType,mapObject,textureMapObj.getX,textureMapObj.getY)
      case _ =>
        throw new IllegalStateException(s"No matching ShapeMapObject found for ${mapObject.getName} with properties: ${mapObject.getProperties}")
    }
  }



  /**
   * Matches for a physic body identifier stored in an object (Identifiers are manually configured as an ID 'bodyType' when objects are created in Tiled)
   * @param bodyTypeAsString stored identifier for type of physic object
   * @return
   */
  private def stringToBodyType(bodyTypeAsString: String): BodyType = {
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

}
