package com.rpg.game.systems.physics.world

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World

/**
 * Wrapper class for Box2D world
 */
class WorldService {
  val world = new World(new Vector2(0, 0), true)
  def stepWorld(deltaTime: Float): Unit = world.step(deltaTime,6,2)
}
