package com.rpg.game.systems.rendering_system

import com.badlogic.gdx.ApplicationAdapter
import com.rpg.game.systems.ListenerSystem

class RenderSystem extends ListenerSystem[RenderListener]{
  
  override def updateListeners(): Unit = {
    listeners.foreach(_.updateListener())
  }

  override def dispose(): Unit = super.dispose()
  
  

}
