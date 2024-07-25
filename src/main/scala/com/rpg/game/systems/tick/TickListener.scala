package com.rpg.game.systems.tick

trait TickListener {
  def updateListener(tick: Long): Unit
}
