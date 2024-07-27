package com.rpg.entity.animate.player

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.{Gdx, Input}
import com.rpg.game.config.map.TiledMapConfig
import com.rpg.game.systems.tick.{TickEvent, TickSystem}

import javax.inject.Inject

class PlayerCameraZoom @Inject(val tickSystem: TickSystem, tiledMapConfig: TiledMapConfig) extends TickEvent {

  tickSystem.addListener(this)
  
  
  override def tick(tick: Long): Unit = {
    cameraZoom()
  }

  private def cameraZoom(): Unit = {
    val up = Gdx.input.isKeyPressed(Input.Keys.NUM_1)
    val down = Gdx.input.isKeyPressed(Input.Keys.NUM_2)

    val camera = tiledMapConfig.viewport.getCamera.asInstanceOf[OrthographicCamera]

    if (up) camera.zoom = camera.zoom - .01f
    if (down) camera.zoom = camera.zoom + .01f

    //limit zoom when not testing

    camera.update()
  }
}
