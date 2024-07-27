package com.rpg.game.systems.tick
//TickEvent
trait TickEvent {
  
  //runTick or tick
  def tick(tick: Long): Unit
}
