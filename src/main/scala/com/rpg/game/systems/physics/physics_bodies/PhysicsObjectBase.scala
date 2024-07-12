package com.rpg.game.systems.physics.physics_bodies

import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.physics.box2d.*
import com.rpg.game.systems.physics.World.WORLD

trait PhysicsObjectBase {

  /**
   * Defines a fixture based on if its dynamic or static
   *
   * @param body     -> body of object
   * @param shape    -> shape of object
   * @param bodyType -> BodyType of object
   * @return
   */
  def getFixtureDef(shape: Shape, bodyType: BodyType): Option[FixtureDef] = {

    if (bodyType == BodyType.DynamicBody) {
      return Some(createDynamicFixtureDef(shape))
    }
    None
  }

  /**
   * Returns a FixtureDef with base settings
   * Note: A FixtureDef is not needed to create static objects
   *
   * @param shape -> shape created from object dimensions
   * @return
   */
  private def createDynamicFixtureDef(shape: Shape): FixtureDef = {
    val fixtureDef = new FixtureDef
    fixtureDef.shape = shape
    fixtureDef.density = 0.5f
    fixtureDef.friction = 1f
    fixtureDef.restitution = 0.0f

    fixtureDef
  }

  /**
   * Returns a base body
   *
   * @param x        -> x coord of body
   * @param y        -> y coord of body
   * @param bodyType -> BodyType defined by object in object layer
   * @return
   */
  def getBodyDef(x: Float, y: Float, bodyType: BodyType): BodyDef = {
    val bodyDef = new BodyDef
    bodyDef.position.set(x, y)
    bodyDef.`type` = bodyType
    if (bodyType == BodyType.DynamicBody) {
      bodyDef.linearDamping = 1f
      bodyDef.fixedRotation = true
    }
    bodyDef
  }

}
