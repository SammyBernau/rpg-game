package com.rpg.test

import com.rpg.game.systems.tick.{TickSystem, TickEvent}

class HumanoidEntityTest(var name: String, tickSystem: TickSystem) extends TickEvent{

  tickSystem.addListener(this)

  override def tick(tick: Long): Unit = {
    if(tick % 5 == 0) name = tick.toString
  }
}
