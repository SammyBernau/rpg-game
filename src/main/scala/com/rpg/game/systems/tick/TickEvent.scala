package com.rpg.game.systems.tick
//TickEvent
trait TickEvent {
  protected val tickSystem: TickSystem
  tickSystem.addListener(this)
  //runTick or tick
  def tick(tick: Long): Unit
}
