package com.rpg.test

import com.badlogic.gdx.graphics.{GL20, Texture}
import com.badlogic.gdx.{Gdx, Screen}
import com.rpg.game.RPG
import com.rpg.game.systems.tick.TickSystem

import java.util.concurrent.CountDownLatch

class SimpleScreen(game: RPG) extends Screen {

  private val timeTickSystem = new TickSystem()
  lazy val sprite = new Texture("badlogic.jpg")
//  val human = new HumanoidEntityTest("sam",timeTickSystem)
//  val animal = new AnimalEntityTest("dog",timeTickSystem)

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



    timeTickSystem.render()

    
    //prints every 5 ticks
    game.font.draw(game.batch, s"MEGATICK: $currentTick", Gdx.graphics.getWidth / 2.toFloat, 50)
    




    game.batch.end()
  }

//class SimpleScreen(game: MainGame) extends Screen {
//
//  private val timeTickSystem = new TimeTickSystem
//  lazy val sprite = new Texture("badlogic.jpg")
//
//  override def show(): Unit = {
//
//  }
//
//  override def render(delta: Float): Unit = {
//    Gdx.gl.glClearColor(1, 0, 0, 1)
//    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
//
//    game.batch.begin()
//
//    val currentTick = timeTickSystem.getCurrentTick
//    //game.batch.draw(sprite, 0, 0)
//    //prints every tick
//    game.font.draw(game.batch, s"Tick: $currentTick", Gdx.graphics.getWidth / 2.toFloat, 100)
//
//
//    timeTickSystem.render()
//
//
//    //prints every 5 ticks
//    timeTickSystem.addTickEventHandler(megaTick => {
//      game.font.draw(game.batch, s"MEGATICK: $megaTick", Gdx.graphics.getWidth / 2.toFloat, 50)
//    }) //currently it prints each megatick multiple times and stops at 20 ticks with thread error
//
//
//    game.batch.end()
//  }

  override def resize(width: Int, height: Int): Unit = {}

  override def pause(): Unit = {}

  override def resume(): Unit = {}

  override def hide(): Unit = {}

  override def dispose(): Unit = {}



}
