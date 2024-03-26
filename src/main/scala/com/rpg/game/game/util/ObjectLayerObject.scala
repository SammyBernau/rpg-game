package com.rpg.game.game.util

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.physics.box2d.*
import com.rpg.game.game.config.CurrentWorld
import com.rpg.game.game.config.GameConfig.GameWorld.WORLD


/**
 * ObjectLayerObject is defined as all objects in the object layer of a tiled map layer.
 * Works for both tiles inserted as objects and blank shapes drawn around tiles.
 * @param name -> name of object
 * @param x -> x coord of object
 * @param y -> y coord of object
 * @param width -> width of object
 * @param height -> height of object
 * @param bodyType -> BodyType defined by object in object layer
 */
class ObjectLayerObject(name: String, x: Float, y: Float, var width: Float, var height: Float, bodyType: BodyType) {
  private val body: Body = getBody(x+(width/2f),y+(height/2f),bodyType)
  var fixture: Fixture = createFixture(bodyType)

  /**
   * Returns a fixture based on object BodyType
   * Currently no legitimate support for kinematic body types
   * @param bodyType -> BodyType defined by object in object layer
   * @return
   */
  private def createFixture(bodyType: BodyType): Fixture = {
    val polygonShape = new PolygonShape()
    polygonShape.setAsBox(width, height)
    val fixture = bodyType match {
      case BodyType.DynamicBody => //dynamic objects
        val fixtureDef: FixtureDef = getDynamicBodyFixtureDef(polygonShape)
        body.createFixture(fixtureDef)
      case BodyType.StaticBody => //static objects
        body.createFixture(polygonShape, 0.0f)
      case BodyType.KinematicBody => //Boiler plate from dynamic so probably wouldnt work here
        val fixtureDef: FixtureDef = getDynamicBodyFixtureDef(polygonShape)
        body.createFixture(fixtureDef)
    }
    polygonShape.dispose()
    fixture
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
