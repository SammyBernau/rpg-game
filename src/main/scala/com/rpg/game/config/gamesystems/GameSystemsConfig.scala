package com.rpg.game.config

import com.rpg.game.config.Config
import com.rpg.game.systems.physics.world.{PhysicsObjectProducer, PhysicsObjectService}
import com.rpg.game.systems.rendering.RenderSystem
import com.rpg.game.systems.rendering.services.gameobjects.{GameObjectCache, ObjectRenderingService, ObjectRenderingServiceHandler}
import com.rpg.game.systems.rendering.services.world.WorldRenderingService
import com.rpg.game.systems.tick.TickSystem

case class GameSystemsConfig(tickSystem: TickSystem,
                             renderSystem: RenderSystem,
                             gameObjectCache: GameObjectCache,
                             physicsObjectService: PhysicsObjectService,
                             physicsObjectProducer: PhysicsObjectProducer,
                             worldRenderingService: WorldRenderingService,
                             objectRenderingService: ObjectRenderingService,
                             objectRenderingServiceHandler: ObjectRenderingServiceHandler) extends Config
