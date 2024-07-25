package com.rpg.game.systems.physics.collision

import com.badlogic.gdx.physics.box2d.Fixture


/**
 * An object that can collide
 */
trait Collidable {
  
  def handleCollision(): Unit 

}
