package com.rpg.game.config

import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.physics.box2d.{Box2DDebugRenderer, World}
import com.badlogic.gdx.utils.viewport.Viewport
import com.rpg.game.config.gamesystems.GameSystemsConfig
import com.rpg.game.config.map.TiledMapConfig
import com.rpg.game.systems.rendering.services.world.WorldRenderingService
import com.rpg.game.systems.rendering.services.gameobjects.{GameObjectCache, ObjectRenderingService}
import com.rpg.game.systems.rendering.{RenderListener, RenderSystem}

import javax.inject.Inject


/**
 * Houses current game configurations and utils
 * @author Sam Bernau
 */

case class CurrentMasterConfig @Inject(tiledMapConfig: TiledMapConfig, gameSystemConfig: GameSystemsConfig) extends ScreenAdapter {

  override def dispose(): Unit = {
    tiledMapConfig.tiledMap.dispose()
    gameSystemConfig.tickSystem.dispose()
    gameSystemConfig.renderSystem.dispose()
    gameSystemConfig.objectRenderingService.dispose()
    gameSystemConfig.worldRenderingService.dispose()
  }
}

class CurrentGameConfigurationHelper @Inject(world: World, val renderSystem: RenderSystem, currentMasterConfiguration: CurrentMasterConfig) extends RenderListener {
  
  private val gameSystemConfiguration = currentMasterConfiguration.gameSystemConfig
  private val mapConfiguration = currentMasterConfiguration.tiledMapConfig

  override def renderListener(): Unit = {
    applyViewport()
    setMapRendererView()
    renderMap()
    renderWorld()
  }

  private def applyViewport(): Unit = {
    mapConfiguration.viewport.apply()
  }

  private def setMapRendererView(): Unit = {
    gameSystemConfiguration.objectRenderingService.setView(mapConfiguration.viewport.getCamera.asInstanceOf[OrthographicCamera])
  }

  private def renderMap(): Unit = {
    gameSystemConfiguration.objectRenderingService.render()
  }

  private def renderWorld(): Unit = {
    gameSystemConfiguration.worldRenderingService.render(world, mapConfiguration.viewport.getCamera.combined)
  }
  
  
}
