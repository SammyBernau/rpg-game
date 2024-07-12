package com.rpg.test

import com.rpg.game.systems.tick.{TickSystem, TickListener}

class HumanoidEntityTest(var name: String, tickSystem: TickSystem) extends TickListener{

  tickSystem.addListener(this)

  override def updateListener(tick: Long): Unit = {
    if(tick % 5 == 0) name = tick.toString
  }
}
