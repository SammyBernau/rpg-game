package com.rpg.entity.animate.player

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.{Gdx, Input}
import com.rpg.game.config.CurrentMasterConfig
import com.rpg.game.systems.tick.{TickListener, TickSystem}

import javax.inject.Inject

class PlayerCameraZoom @Inject(tickSystem: TickSystem, currentMasterConfig: CurrentMasterConfig) extends TickListener {

  tickSystem.addListener(this)

  private val mapConfig = currentMasterConfig.tiledMapConfig
  
  override def updateListener(tick: Long): Unit = {
    cameraZoom()
  }

  private def cameraZoom(): Unit = {
    val up = Gdx.input.isKeyPressed(Input.Keys.NUM_1)
    val down = Gdx.input.isKeyPressed(Input.Keys.NUM_2)

    val camera = mapConfig.viewport.getCamera.asInstanceOf[OrthographicCamera]

    if (up) camera.zoom = camera.zoom - .01f
    if (down) camera.zoom = camera.zoom + .01f

    //limit zoom when not testing

    camera.update()
  }
}
