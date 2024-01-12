package com.rpg.game.tick

import com.badlogic.gdx.{Application, ApplicationAdapter, Gdx, Screen, ScreenAdapter}

import scala.jdk.CollectionConverters.*
import java.util.concurrent.{Callable, ConcurrentLinkedQueue, CountDownLatch, ExecutorService, Executors}
import scala.collection.mutable
import scala.concurrent.{ExecutionContext, Future}


class Tick extends ApplicationAdapter {

  private val TICK_TIMER_MAX: Float = .2f //every .2f == 200 milliseconds or 5 ticks per second
  private var tick: Long = 0
  private var tickTimer: Float = 0.0f
  
  private var listeners: List[TickListener] = List()
  
  //TODO use threadPool

  def addListener(listener: TickListener): Unit = synchronized { //synchronized creates a "queue" (locks and unlocks) to make it thread safe
    listeners = listener :: listeners
  }

  def removeListener(listener: TickListener): Unit = synchronized {
    listeners = listeners.filterNot(_ == listener)
  }

  def getCurrentTick: Long = tick

  override def create(): Unit = {
    tick = 0
  }

  //dont thread the render method when its called until its a problem
  override def render(): Unit = {
      tickTimer += Gdx.graphics.getDeltaTime
      if (tickTimer >= TICK_TIMER_MAX) {
        tickTimer -= TICK_TIMER_MAX
        tick += 1

            //for now only thread this
            //TODO come up with a unified way to collect only the updates that need updating
            listeners.foreach(_.update(tick)) //pass tick to update method for other classes to define later

      }
  }


  //clear resources
  override def dispose(): Unit = {
    listeners = List()
  }

}

trait TickListener {
  def update(tick: Long): Unit
}
