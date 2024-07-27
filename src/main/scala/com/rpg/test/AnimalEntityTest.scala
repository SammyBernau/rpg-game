//package com.rpg.test
//
//import com.rpg.game.systems.tick.{TickSystem, TickEvent}
//
//class AnimalEntityTest (var sound: String, tickSystem: TickSystem) extends TickEvent {
//
//  tickSystem.addListener(this)
//
//  override def tick(tick: Long): Unit = {
//    if (tick % 5 == 0) sound = (tick * 2).toString
//  }
//
//}
