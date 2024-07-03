package com.rpg.game.systems.tick_system

trait TickListener {
  def updateListener(tick: Long): Unit
}
