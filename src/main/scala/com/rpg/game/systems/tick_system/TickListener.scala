package com.rpg.game.systems.tick_system

trait TickListener {
  def update(tick: Long): Unit
}
