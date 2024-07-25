package com.rpg.game.systems.physics.world

import com.badlogic.gdx.physics.box2d.Body


/**
 * Wrapper for Box2D body class
 * @param body -> Box2D body
 */
case class ObjectBody(body: Body) {
  def setObjectData(data: ObjectData): Unit = body.setUserData(data)
}
