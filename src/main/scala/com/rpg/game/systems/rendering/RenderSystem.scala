package com.rpg.game.systems.rendering

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.graphics.OrthographicCamera
import com.rpg.game.config.CurrentMasterConfig
import com.rpg.game.systems.EventSystem

import javax.inject.Inject

class RenderSystem extends EventSystem[RenderListener]{

//  override def render(): Unit = {
//    currentSettings.viewport.apply()
//    currentSettings.mapRenderer.setView(currentSettings.viewport.getCamera.asInstanceOf[OrthographicCamera])
//    currentSettings.mapRenderer.render()
//    currentSettings.worldRenderer.render(WORLD, currentSettings.viewport.getCamera.combined)
//  }

  override def updateListeners(): Unit = {
    listeners.foreach(_.renderListener())
  }
  
  override def dispose(): Unit = super.dispose()
  
  

}
