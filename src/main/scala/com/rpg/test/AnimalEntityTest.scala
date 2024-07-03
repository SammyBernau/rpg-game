package com.rpg.test

import com.rpg.game.systems.tick_system.{TickSystem, TickListener}

class AnimalEntityTest (var sound: String, tickSystem: TickSystem) extends TickListener {

  tickSystem.addListener(this)

  override def updateListener(tick: Long): Unit = {
    if (tick % 5 == 0) sound = (tick * 2).toString
  }

}
