package com.rpg.game.game.util.physics.fixture

import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.physics.box2d.*
import com.rpg.game.game.config.GameConfig.GameWorld.WORLD

trait FixtureBase {

  /**
   * Defines a fixture based on if its dynamic or static
   *
   * @param body     -> body of object
   * @param shape    -> shape of object
   * @param bodyType -> BodyType of object
   * @return
   */
  def defineFixture(body: Body, shape: Shape, bodyType: BodyType): Fixture = {

    if (bodyType == BodyType.DynamicBody) {
      val fixtureDef: FixtureDef = getDynamicBodyFixtureDef(shape)
      body.createFixture(fixtureDef)
    }

    body.createFixture(shape, 0.0f)
  }

  /**
   * Returns a FixtureDef with base settings
   * Note: A FixtureDef is not needed to create static objects
   *
   * @param shape -> shape created from object dimensions
   * @return
   */
  def getDynamicBodyFixtureDef(shape: Shape): FixtureDef = {
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
  def getBody(x: Float, y: Float, bodyType: BodyType): Body = {
    val bodyDef = new BodyDef
    bodyDef.position.set(x, y)
    bodyDef.`type` = bodyType
    if (bodyType == BodyType.DynamicBody) {
      bodyDef.linearDamping = 1f
      bodyDef.fixedRotation = true
    }
    WORLD.createBody(bodyDef)
  }

}
