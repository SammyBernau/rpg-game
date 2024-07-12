package com.rpg.game.config

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.physics.box2d.{Box2DDebugRenderer, World}
import com.badlogic.gdx.utils.viewport.Viewport
import com.rpg.game.systems.physics.World.WORLD
import com.rpg.game.systems.rendering.services.{GameObjectCache, ObjectRenderingService}
import com.rpg.game.systems.rendering.{RenderListener, RenderSystem}

import javax.inject.Inject


/**
 * Houses current game settings and utils
 *
 * @param viewport -> current viewport of game
 * @param objectRenderingService -> current map renderer
 * @param tiledMap -> current Tiled map
 * @param worldRenderer -> current world renderer
 * @author Sam Bernau
 */

case class CurrentSettings (viewport: Viewport, objectRenderingService: ObjectRenderingService,
                            tiledMap: TiledMap, worldRenderer: Box2DDebugRenderer,gameObjectCache: GameObjectCache){
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
    currentSettings.objectRenderingService.setView(currentSettings.viewport.getCamera.asInstanceOf[OrthographicCamera])
  }
  
  private def renderMap(): Unit = {
    currentSettings.objectRenderingService.render()
  }
  
  private def renderWorld(): Unit = {
    currentSettings.worldRenderer.render(WORLD, currentSettings.viewport.getCamera.combined)
  }
  
  
}
