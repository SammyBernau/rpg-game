package com.rpg.game.config.system

import com.badlogic.gdx.maps.tiled.TiledMap
import com.rpg.game.config.{ConfigService, GameSystemsConfig}
import com.rpg.game.systems.physics.world.{PhysicsObjectProducer, PhysicsObjectService}
import com.rpg.game.systems.rendering.RenderSystem
import com.rpg.game.systems.rendering.services.world.WorldRenderingService
import com.rpg.game.systems.rendering.services.gameobjects.{GameObjectCache, ObjectRenderingService, ObjectRenderingServiceHandler}
import com.rpg.game.systems.tick.TickSystem

class GameSystemsConfigService(tiledMap: TiledMap) extends ConfigService {

  override def loadConfig(): GameSystemsConfig = {
    val tickSystem = new TickSystem()
    val renderSystem = new RenderSystem()
    val gameObjectCache = GameObjectCache()
    val physicsObjectService = PhysicsObjectService()
    val physicsObjectProducer = new PhysicsObjectProducer(physicsObjectService)

    val worldRenderingService = new WorldRenderingService
    val objectRenderingService = new ObjectRenderingService(gameObjectCache,tiledMap)
    val objectRenderingServiceHelper = new ObjectRenderingServiceHandler(gameObjectCache,physicsObjectProducer,tiledMap)

    GameSystemsConfig(tickSystem, renderSystem, gameObjectCache, physicsObjectService, physicsObjectProducer,worldRenderingService, objectRenderingService,objectRenderingServiceHelper)
  }


}
