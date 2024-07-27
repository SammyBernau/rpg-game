package com.rpg.game.config.gamesystems

import com.rpg.entity.item.projectiles.projectile_systems.ProjectileMoveCache
import com.rpg.game.config.Config
import com.rpg.game.systems.physics.world.remove.{RemoveObjectProducer, RemoveObjectCache}
import com.rpg.game.systems.physics.world.add.{PhysicsObjectProducer, PhysicsObjectCache}
import com.rpg.game.systems.rendering.RenderSystem
import com.rpg.game.systems.rendering.services.gameobjects.{GameObjectCache, ObjectRenderingService, ObjectRenderingServiceHandler}
import com.rpg.game.systems.rendering.services.world.WorldRenderingService
import com.rpg.game.systems.tick.TickSystem

import javax.inject.Inject

case class GameSystemsConfig (tickSystem: TickSystem,
                              renderSystem: RenderSystem,
                              gameObjectCache: GameObjectCache,
                              physicsObjectService: PhysicsObjectCache,
                              physicsObjectProducer: PhysicsObjectProducer,
                              worldRenderingService: WorldRenderingService,
                              objectRenderingService: ObjectRenderingService,
                              objectRenderingServiceHandler: ObjectRenderingServiceHandler,
                              projectileMoveService: ProjectileMoveCache,
                              removeObjectProducer: RemoveObjectProducer,
                              removeObjectService: RemoveObjectCache) extends Config
