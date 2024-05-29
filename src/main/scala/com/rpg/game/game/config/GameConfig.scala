package com.rpg.game.game.config

import com.badlogic.gdx.{Gdx, Input}
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World


/**
 * Common configs used across files
 */
object GameConfig {
  object GameWorld {
    val WORLD = new World(new Vector2(0,0),true)
    val PIXEL_PER_METER: Float = 1/100f //1 meter per 100 pixels
    val W: Boolean = Gdx.input.isKeyPressed(Input.Keys.W)
    val A: Boolean = Gdx.input.isKeyPressed(Input.Keys.A)
    val S: Boolean = Gdx.input.isKeyPressed(Input.Keys.S)
    val D: Boolean = Gdx.input.isKeyPressed(Input.Keys.D)
    val SPACE: Boolean = Gdx.input.isKeyPressed(Input.Keys.SPACE)
    val SHIFT: Boolean = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)
  }
  
  

}
