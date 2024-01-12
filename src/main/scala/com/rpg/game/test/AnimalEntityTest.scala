//package com.rpg.game.test
//
//import com.rpg.game.tick.{TickListener, TimeTickSystem}
//
//class AnimalEntityTest (var sound: String, tickSystem: TimeTickSystem) extends TickListener{
//
//  tickSystem.registerListener(this)
//  
//  override def update(tick: Long): Unit = {
//    if (tick % 5 == 0) sound = (tick * 2).toString
//  }
//
//}
