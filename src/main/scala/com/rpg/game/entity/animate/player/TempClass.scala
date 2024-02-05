package com.rpg.game.entity.animate.player

import com.artemis.PooledComponent

class TempClass extends PooledComponent{
  private var touchedPlatforms = 0

  private var diamondsCollected = 0

  override def reset(): Unit = {
    touchedPlatforms = 0
    diamondsCollected = 0
  }
}
