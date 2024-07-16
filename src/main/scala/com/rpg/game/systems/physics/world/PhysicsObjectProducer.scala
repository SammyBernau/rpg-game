package com.rpg.game.systems.physics.world

import com.badlogic.gdx.graphics.g3d.utils.shapebuilders.EllipseShapeBuilder
import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.maps.objects.*
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject
import com.badlogic.gdx.math.{Polygon, Vector2}
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.rpg.game.structure.Producer
import com.rpg.game.systems.physics.bodies.shapes.*
import com.rpg.game.systems.physics.bodies.{PhysicsObjectFactory, PhysicsObjectSimple}
import com.rpg.game.systems.physics.world.{PhysicsObjectDefWrapper, PhysicsObjectService}

import javax.inject.Inject


/**
 * An ObjectLayerObject is defined as an object in the object layer of a tiled map layer.
 * Works for both tiles inserted as objects and free drawn objects with no textures.
 *
 * @author Sam Bernau
 */

//TODO -> add collision filtering
class PhysicsObjectProducer @Inject(physicsObjectService: PhysicsObjectService) extends Producer[MapObject] {

  // Returns a fixtureDef and bodyDef based on object settings
  // Currently no legitimate support for kinematic body types
  private val physicsObjectFactory = PhysicsObjectFactory
  

  override def produce(mapObject: MapObject): Unit = {
    val bodyType = try {
      stringToBodyType(mapObject.getProperties.get("BodyType").toString)
    } catch {
      case e: Exception =>
        //        println(e.getStackTrace.mkString("Array(", ", ", ")"))
        println(s"BodyType is either null or incorrectly set for this object: ${mapObject.getName}")
        println("...Setting BodyType to default: Static")
        BodyType.StaticBody
    }
    

    val startedRequest = physicsObjectFactory.create(mapObject)
    val completedRequest = startedRequest.getDefs(bodyType, mapObject)
    
    physicsObjectService.add(completedRequest)
  }





  /**
   * Matches for a physic body identifier stored in an object (Identifiers are manually configured as an ID 'bodyType' when objects are created in Tiled)
   * @param bodyTypeAsString stored identifier for type of physic object
   * @return
   */
  private def stringToBodyType(bodyTypeAsString: String): BodyType = {
    bodyTypeAsString.toLowerCase match {
      case "dynamic" => BodyType.DynamicBody
      case "dynamicBody" => BodyType.DynamicBody

      case "static" => BodyType.StaticBody
      case "staticbody" => BodyType.StaticBody

      case "kinematic" => BodyType.KinematicBody
      case "kinematicbody" => BodyType.KinematicBody

      case _ => throw new IllegalArgumentException("Invalid BodyType or BodyType set incorrectly in Tiled object properties")
    }
  }

}
