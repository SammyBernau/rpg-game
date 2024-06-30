package com.rpg.game.game.util.physics.collision

import com.badlogic.gdx.physics.box2d.Fixture


/**
 * An object that can collide
 */
trait Collidable {
  
  def handleCollision(): Unit

}
