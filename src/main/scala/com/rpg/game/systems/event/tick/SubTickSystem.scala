package com.rpg.game.systems.event.tick

import com.badlogic.gdx.Gdx
import com.rpg.game.systems.EventSystem

import java.util.concurrent.Executors
import scala.concurrent.{ExecutionContext, ExecutionContextExecutor, Future}
import javax.inject.Singleton


@Singleton
class SubTickSystem extends EventSystem[SubTickEvent] {


  val TICK_TIMER_MAX: Float = 1.0f / 50000.0f //50,000 ticks a second
  private var tick: Long = 0
  private var tickTimer: Float = 0.0f

  private val threadPool = Executors.newFixedThreadPool(10)
  implicit val executionContext: ExecutionContextExecutor = ExecutionContext.fromExecutor(threadPool)


  def getCurrentTick: Long = tick
  def getDeltaTime: Float = Gdx.graphics.getDeltaTime

  override def render(): Unit = {
    tickTimer += getDeltaTime
    if (tickTimer >= TICK_TIMER_MAX) {
      tickTimer -= TICK_TIMER_MAX
      tick += 1
    }
  }

  override def updateEvents(): Unit = {
    //Future to run each update in a separate thread
    events.foreach { event =>
        event.tick(getCurrentTick)
    }
  }

  //clear resources
  override def dispose(): Unit = {
    threadPool.shutdown()
    super.dispose()
  }
}
