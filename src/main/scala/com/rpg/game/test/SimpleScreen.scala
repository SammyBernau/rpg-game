package com.rpg.game.test

import com.badlogic.gdx.graphics.{GL20, Texture}
import com.badlogic.gdx.{Gdx, Screen}
import com.rpg.game.tick.TimeTickSystem

class SimpleScreen(game: MainGame) extends Screen {

  private val timeTickSystem = new TimeTickSystem
  lazy val sprite = new Texture("badlogic.jpg")

  override def show(): Unit = {}

  override def render(delta: Float): Unit = {
    Gdx.gl.glClearColor(1, 0, 0, 1)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    game.batch.begin()
    timeTickSystem.render()
    val tick = timeTickSystem.getCurrentTick
    game.batch.draw(sprite, 0, 0)
    game.font.draw(game.batch,s"Tick: $tick", Gdx.graphics.getWidth/2.toFloat, 100) //updates every tick
    timeTickSystem.addTickEventHandler(tick => {
      game.font.draw(game.batch,s"MEGATICK: $tick", Gdx.graphics.getWidth/2.toFloat, 50) //event to print every tick 5th tick
    })
    game.batch.end()
  }

  override def resize(width: Int, height: Int): Unit = {}

  override def pause(): Unit = {}

  override def resume(): Unit = {}

  override def hide(): Unit = {}

  override def dispose(): Unit = {}



}
