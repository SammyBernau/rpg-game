package com.rpg.game.test

import com.rpg.game.tick.{TickListener, Tick}

class HumanoidEntityTest(var name: String, tickSystem: Tick) extends TickListener{

  tickSystem.addListener(this)

  override def update(tick: Long): Unit = {
    if(tick % 5 == 0) name = tick.toString
  }
}
