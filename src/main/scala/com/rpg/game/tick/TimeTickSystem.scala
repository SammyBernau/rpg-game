package com.rpg.game.tick

import com.badlogic.gdx.{Application, ApplicationAdapter, Gdx, Screen, ScreenAdapter}

import scala.jdk.CollectionConverters.*
import java.util.concurrent.{Callable, ConcurrentLinkedQueue, CountDownLatch, ExecutorService, Executors}
import scala.collection.mutable
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

class TimeTickSystem extends ApplicationAdapter {

  private val TICK_TIMER_MAX: Float = .2f //every .2f == 200 milliseconds or 5 ticks per second
  private var tick: Long = 0
  private var tickTimer: Float = 0.0f


  //list of event handlers
  private val tickEventHandlers: mutable.ListBuffer[Long => Unit] = mutable.ListBuffer()

  //Thread pool
  private val executorService: ExecutorService = Executors.newFixedThreadPool(3)
  
  private implicit val ec: ExecutionContext = ExecutionContext.global

  //Results queue
  private val results: ConcurrentLinkedQueue[Long] = new ConcurrentLinkedQueue[Long]



  def getCurrentTick: Long = tick

  def addTickEventHandler(handler: Long => Unit): Unit = {
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
      
      //3 threads to play with for handler, 1 for display, 1 tickSystem (spins off 3 other threads to handle results)
      //blocking so multi-thread
      if(tick % 5 == 0) {
        val futures = tickEventHandlers.map(handler => {
          Future {
            handler(tick)
            tick //returned as result of Future
          }
        })

        futures.foreach(future => {
          future.onComplete {
            case Success(value) => results.add(value)
            case Failure(e) => e.printStackTrace()
          }
        })
      }
    }
  }

  def getTickResults: List[Long] = results.iterator().asScala.toList
}
