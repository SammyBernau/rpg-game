package com.rpg.test

import com.rpg.game.systems.tick_system.{Tick, TickListener}

class AnimalEntityTest (var sound: String, tickSystem: Tick) extends TickListener {

  tickSystem.addListener(this)

  override def update(tick: Long): Unit = {
    if (tick % 5 == 0) sound = (tick * 2).toString
  }

}
