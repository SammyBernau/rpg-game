package com.rpg.game.game.systems.objects

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.physics.box2d.*
import com.rpg.game.game.OrthogonalTiledMapRendererWithObjects
import com.rpg.game.game.config.CurrentWorld
import com.rpg.game.game.config.GameConfig.GameWorld.WORLD

class ObjectLayerObject(name: String, x: Float, y: Float, var width: Float, var height: Float, bodyType: BodyType) {
  width = width / 2f
  height = height / 2f
  private val bodyDef: BodyDef = getBodyDef(x+width,y+height,bodyType)
  private val body: Body = WORLD.createBody(bodyDef)
  private val polygonShape = new PolygonShape()
  polygonShape.setAsBox(width,height)
  var fixture: Fixture = _
  
  if(bodyType == BodyType.DynamicBody) { //dynamic objects
    val fixtureDef: FixtureDef = getDynamicBodyFixture(polygonShape)
    fixture = body.createFixture(fixtureDef)
  } else if (bodyType == BodyType.StaticBody) { //static objects
    fixture = body.createFixture(polygonShape,0.0f)
  }
  polygonShape.dispose()

  /**
   * Defines a base fixture for all objects
   *
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
  private def getBodyDef(x: Float, y: Float, bodyType: BodyType): BodyDef = {
    val bodyDef = new BodyDef
    bodyDef.position.set(x, y)
    bodyDef.`type` = bodyType
    if(bodyType == BodyType.DynamicBody) {
      bodyDef.linearDamping = 1f
      bodyDef.fixedRotation = true
    }

    bodyDef
  }

}
