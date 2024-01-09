package com.rpg.game.tick

import com.badlogic.gdx.{ApplicationAdapter, Gdx, Screen, ScreenAdapter}

import scala.collection.mutable

class TimeTickSystem extends ApplicationAdapter {
  
  private val TICK_TIMER_MAX: Float = .2f //every .2f == 200 milliseconds or 5 ticks per second
  private var tick: Int = 0
  private var tickTimer: Float = 0.0f
  
  
  //list of event handlers
  private val tickEventHandlers: mutable.ListBuffer[Int => Unit] = mutable.ListBuffer()
  
  def getCurrentTick: Int = tick
  
  def addTickEventHandler(handler: Int => Unit): Unit = {
    tickEventHandlers += handler
  }
  
  override def create(): Unit = {
    tick = 0
  }


  override def render(): Unit = {
    tickTimer += Gdx.graphics.getDeltaTime

    if(tickTimer >= TICK_TIMER_MAX){ 
      tickTimer -= TICK_TIMER_MAX
      tick += 1
      
      if(tick % 5 == 0) tickEventHandlers.foreach(handler => handler(tick))
    }
  }
  
  
}
