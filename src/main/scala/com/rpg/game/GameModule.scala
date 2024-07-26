package com.rpg.game

import com.badlogic.gdx.physics.box2d.World
import com.google.inject.AbstractModule
import com.rpg.entity.animate.player.{PlayerMovement, PlayerAnimation, PlayerCameraZoom}
import com.rpg.entity.item.projectiles.ProjectileSystem
import com.rpg.entity.item.projectiles.projectile_systems.{GhostFireballSystem, ProjectileMoveConsumer, ProjectileMoveService}
import com.rpg.game.config.{CurrentGameConfigurationHelper, CurrentMasterConfig}
import com.rpg.game.systems.ListenerSystem
import com.rpg.game.systems.physics.world.add.{PhysicsObjectConsumer, PhysicsObjectService}
import com.rpg.game.systems.physics.world.remove.{RemoveObjectConsumer, RemoveObjectService}
import com.rpg.game.systems.rendering.RenderSystem
import com.rpg.game.systems.rendering.services.gameobjects.GameObjectCache
import com.rpg.game.systems.tick.{TickListener, TickSystem}

import scala.reflect.ClassTag

class GameModule(world: World, currentMasterConfig: CurrentMasterConfig) extends AbstractModule{

  private val gameSystemsConfig = currentMasterConfig.gameSystemConfig
  private val renderSystem = gameSystemsConfig.renderSystem
  private val gameObjectCache = gameSystemsConfig.gameObjectCache

  override def configure(): Unit = {

    //World
    bind(classOf[World]).toInstance(world)

    //Game systems and caches
    bind(classOf[CurrentMasterConfig]).toInstance(currentMasterConfig)
    bind(classOf[GameObjectCache]).asEagerSingleton()
    bind(classOf[PhysicsObjectService]).asEagerSingleton()
    bind(classOf[ProjectileMoveService]).asEagerSingleton()
    bind(classOf[RemoveObjectService]).asEagerSingleton()

    //RenderSystem (performs updates synchronously)
    bind(classOf[RenderSystem]).toInstance(currentMasterConfig.gameSystemConfig.renderSystem)
    //---Children of RenderSystem---
//    bind(classOf[PhysicsObjectConsumer]).toInstance(new PhysicsObjectConsumer(
//      currentMasterConfig.gameSystemConfig.renderSystem,
//      world,
//      gameObjectCache,
//      gameSystemsConfig.physicsObjectService))
//    bind(classOf[RemoveObjectConsumer]).toInstance(new RemoveObjectConsumer(
//      renderSystem,
//      world,
//      gameSystemsConfig.removeObjectService,
//      gameSystemsConfig.objectRenderingServiceHandler))
//
//    bind(classOf[ProjectileMoveConsumer]).toInstance(new ProjectileMoveConsumer(
//        renderSystem,
//        gameObjectCache,
//        gameSystemsConfig.projectileMoveService))

    //bind(classOf[CurrentSettingsHelper]).toInstance(new CurrentSettingsHelper(renderSystem, currentSettings))


    //TickSystem (performs updates asynchronously)
    bind(classOf[TickSystem]).toInstance(currentMasterConfig.gameSystemConfig.tickSystem)
    //---Children of TickSystem---
    bind(classOf[PlayerMovement]).asEagerSingleton()
    bind(classOf[GhostFireballSystem]).asEagerSingleton()
    bind(classOf[PlayerAnimation]).asEagerSingleton()
    bind(classOf[PlayerCameraZoom]).asEagerSingleton()



  }



}
