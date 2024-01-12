package com.rpg.game.test

import com.rpg.game.ticksystem.{TickListener, Tick}

class AnimalEntityTest (var sound: String, tickSystem: Tick) extends TickListener{

  tickSystem.addListener(this)

  override def update(tick: Long): Unit = {
    if (tick % 5 == 0) sound = (tick * 2).toString
  }

}
