package com.rpg.game.systems.physics_system

import com.badlogic.gdx.graphics.g3d.utils.shapebuilders.EllipseShapeBuilder
import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.maps.objects.{EllipseMapObject, PolygonMapObject, PolylineMapObject, RectangleMapObject, TextureMapObject}
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject
import com.badlogic.gdx.math.{Polygon, Vector2}
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.rpg.game.systems.physics_system.physics_bodies.{FixtureCreatorFactory, FixtureCreatorSimple}
import com.rpg.game.systems.physics_system.physics_bodies.shapes.{EllipseObject, PolygonObject, PolylineObject, PolylineObjectBoundingBox, RectangleObject, TextureObject}


/**
 * An ObjectLayerObject is defined as an object in the object layer of a tiled map layer.
 * Works for both tiles inserted as objects and free drawn objects with no textures.
 *
 * @param mapObject -> current mapobject
 * @author Sam Bernau
 */

//TODO -> add collision filtering
class ObjectLayerObject(mapObject: MapObject) {

  // Returns a fixture based on object settings
  // Currently no legitimate support for kinematic body types
  private val fixtureCreator: FixtureCreatorSimple = FixtureCreatorFactory.getFixtureCreator(mapObject)
  val fixture: Fixture = buildFixture()

  private def buildFixture(): Fixture = {
    val bodyType = try {
      stringToBodyType(mapObject.getProperties.get("BodyType").toString)
    } catch {
      case e: Exception =>
//        println(e.getStackTrace.mkString("Array(", ", ", ")"))
        println(s"BodyType is either null or incorrectly set for this object: ${mapObject.getName}")
        println("...Setting BodyType to default: Static")
        BodyType.StaticBody
    }

    fixtureCreator.getFixture(bodyType,mapObject)
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
