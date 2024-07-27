package com.rpg.game.systems.tick

import com.badlogic.gdx.{Application, ApplicationAdapter, Gdx, Screen, ScreenAdapter}
import com.rpg.game.systems.EventSystem

import scala.jdk.CollectionConverters.*
import java.util.concurrent.{Callable, ConcurrentLinkedQueue, CountDownLatch, ExecutorService, Executors}
import javax.inject.Inject
import scala.collection.mutable
import scala.concurrent.{ExecutionContext, ExecutionContextExecutor, Future}
import javax.inject.{Inject, Singleton}

@Singleton
class TickSystem extends EventSystem[TickEvent]  {
  
  //every .2f == 200 milliseconds or 5 ticks per second
  //every .1f == 100 milliseconds or 10 ticks per second
  private val TICK_TIMER_MAX: Float = .2f
  private var tick: Long = 0
  private var tickTimer: Float = 0.0f

  //Multithreading
  private val threadPool = Executors.newFixedThreadPool(10)
  implicit val executionContext: ExecutionContextExecutor = ExecutionContext.fromExecutor(threadPool)

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
        updateListeners()
      }
  }

  override def updateListeners(): Unit = {
    //Future to run each update in a separate thread
    listeners.foreach{listener =>
      Future {
        listener.tick(getCurrentTick)
      }
    }
  }

  //clear resources
  override def dispose(): Unit = {
    threadPool.shutdown()
    super.dispose()
  }

}
