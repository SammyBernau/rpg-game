package com.rpg.game.systems.concurrent

import com.google.inject.Singleton

import java.util.concurrent.Executors
import scala.concurrent.{ExecutionContext, ExecutionContextExecutor, Future}

@Singleton
class Scheduler {

  private val threadPool = Executors.newFixedThreadPool(10)
  implicit val executionContext: ExecutionContextExecutor = ExecutionContext.fromExecutor(threadPool)

  def runAsync(func: ()=> Any): Unit = {
    Future {
      func()
    }
  }

}
