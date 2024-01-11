package com.rpg.game.test

import com.badlogic.gdx.graphics.{GL20, Texture}
import com.badlogic.gdx.{Gdx, Screen}
import com.rpg.game.tick.TimeTickSystem

import java.util.concurrent.CountDownLatch

class SimpleScreen(game: MainGame) extends Screen {

  private val timeTickSystem = new TimeTickSystem
  lazy val sprite = new Texture("badlogic.jpg")

  override def show(): Unit = {

  }

  override def render(delta: Float): Unit = {
    Gdx.gl.glClearColor(1, 0, 0, 1)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

    game.batch.begin()

    val currentTick = timeTickSystem.getCurrentTick
    //game.batch.draw(sprite, 0, 0)
    //prints every tick
    game.font.draw(game.batch,s"Tick: $currentTick", Gdx.graphics.getWidth/2.toFloat, 100)


    //pass render into its own thread
    timeTickSystem.render()


    //prints every 5 ticks
    timeTickSystem.getTickResults.foreach(megaTick => {
      game.font.draw(game.batch, s"MEGATICK: $megaTick", Gdx.graphics.getWidth / 2.toFloat, 50)
    }) //currently it prints each megatick multiple times and stops at 20 ticks with thread error




    
    game.batch.end()
  }

  override def resize(width: Int, height: Int): Unit = {}

  override def pause(): Unit = {}

  override def resume(): Unit = {}

  override def hide(): Unit = {}

  override def dispose(): Unit = {}



}
