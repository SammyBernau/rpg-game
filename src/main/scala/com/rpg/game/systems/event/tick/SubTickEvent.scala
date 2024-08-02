package com.rpg.game.systems.event.tick

trait SubTickEvent {
  protected val subTickSystem: SubTickSystem
  subTickSystem.addEvent(this)

  def tick(tick: Long): Unit
}
