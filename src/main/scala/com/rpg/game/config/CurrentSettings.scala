package com.rpg.game.config

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.physics.box2d.{Box2DDebugRenderer, World}
import com.badlogic.gdx.utils.viewport.Viewport
import com.rpg.game.RendererWithObjects
import com.rpg.game.systems.physics_system.World.WORLD
import com.rpg.game.systems.rendering_system.{RenderListener, RenderSystem}

import javax.inject.Inject


/**
 * Houses current game settings and utils
 *
 * @param viewport -> current viewport of game
 * @param mapRenderer -> current map renderer
 * @param tiledMap -> current Tiled map
 * @param worldRenderer -> current world renderer
 * @author Sam Bernau
 */

case class CurrentSettings (viewport: Viewport, mapRenderer: RendererWithObjects,
                           tiledMap: TiledMap, worldRenderer: Box2DDebugRenderer){
}

class CurrentSettingsHelper @Inject(renderSystem: RenderSystem, currentSettings: CurrentSettings) extends RenderListener {
  
  renderSystem.addListener(this)

  override def renderListener(): Unit = {
    applyViewport()
    setMapRendererView()
    renderMap()
    renderWorld()
  }
  
  private def applyViewport(): Unit = {
    currentSettings.viewport.apply()
  }
  
  private def setMapRendererView(): Unit = {
    currentSettings.mapRenderer.setView(currentSettings.viewport.getCamera.asInstanceOf[OrthographicCamera])
  }
  
  private def renderMap(): Unit = {
    currentSettings.mapRenderer.render()
  }
  
  private def renderWorld(): Unit = {
    currentSettings.worldRenderer.render(WORLD, currentSettings.viewport.getCamera.combined)
  }
  
  
}
