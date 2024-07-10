package com.rpg.game.systems.physics_system

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World

object World {
  val WORLD = new World(new Vector2(0,0),true)
  val PIXEL_PER_METER: Float = 1/100f //1 meter per 100 pixels
}
