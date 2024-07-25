package com.rpg.game.config.gamesystems

import com.badlogic.gdx.maps.tiled.TiledMap
import com.rpg.entity.item.projectiles.projectile_systems.ProjectileMoveService
import com.rpg.game.config.ConfigService
import com.rpg.game.systems.physics.world.remove.{RemoveObjectProducer, RemoveObjectService}
import com.rpg.game.systems.physics.world.add.{PhysicsObjectProducer, PhysicsObjectService}
import com.rpg.game.systems.rendering.RenderSystem
import com.rpg.game.systems.rendering.services.world.WorldRenderingService
import com.rpg.game.systems.rendering.services.gameobjects.{GameObjectCache, ObjectRenderingService, ObjectRenderingServiceHandler}
import com.rpg.game.systems.tick.TickSystem

class GameSystemsConfigService(tiledMap: TiledMap) extends ConfigService {

  override def loadConfig(): GameSystemsConfig = {
    val tickSystem = new TickSystem()
    val renderSystem = new RenderSystem()
    val gameObjectCache = new GameObjectCache()
    val physicsObjectService = new PhysicsObjectService()
    val physicsObjectProducer = new PhysicsObjectProducer(physicsObjectService)
    val projectileMoveService = new ProjectileMoveService()
    
    val removeObjectService = new RemoveObjectService()
    val removeObjectProducer = new RemoveObjectProducer(removeObjectService)
    
    

    val worldRenderingService = new WorldRenderingService
    val objectRenderingService = new ObjectRenderingService(gameObjectCache,tiledMap)
    val objectRenderingServiceHandler = new ObjectRenderingServiceHandler(gameObjectCache,physicsObjectProducer,tiledMap)

    GameSystemsConfig(
      tickSystem, 
      renderSystem, 
      gameObjectCache, 
      physicsObjectService, 
      physicsObjectProducer,
      worldRenderingService, 
      objectRenderingService,
      objectRenderingServiceHandler,
      projectileMoveService,
      removeObjectProducer, removeObjectService
    )
  }


}
