package com.rpg.game.systems.tick_system

import com.badlogic.gdx.{Application, ApplicationAdapter, Gdx, Screen, ScreenAdapter}

import scala.jdk.CollectionConverters.*
import java.util.concurrent.{Callable, ConcurrentLinkedQueue, CountDownLatch, ExecutorService, Executors}
import scala.collection.mutable
import scala.concurrent.{ExecutionContext, ExecutionContextExecutor, Future}

//TODO check out interpolation in libGDX
class Tick extends ApplicationAdapter {

  //every .2f == 200 milliseconds or 5 ticks per second
  //every .1f == 100 milliseconds or 10 ticks per second
  private val TICK_TIMER_MAX: Float = .2f
  private var tick: Long = 0
  private var tickTimer: Float = 0.0f

  //Holds all listeners that need updating
  private var listeners: List[TickListener] = List()

  //Multithreading
  private val threadPool = Executors.newFixedThreadPool(10)
  implicit val executionContext: ExecutionContextExecutor = ExecutionContext.fromExecutor(threadPool)

  
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
      }
  }

  //TODO come up with a unified way to collect only the updates that need updating
  //for now only thread this
  //def update(): Unit = listeners.foreach(_.update(getCurrentTick)) //pass tick to update method for other classes to define later

  def update(): Unit = {
    //Future to run each update in a separate thread
    listeners.foreach{listener =>
      Future {
        listener.update(getCurrentTick)
      }
    }
  }

  //clear resources
  override def dispose(): Unit = {
    listeners = List()
    threadPool.shutdown()
  }

}
