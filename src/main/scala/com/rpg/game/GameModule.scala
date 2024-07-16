package com.rpg.game

import com.google.inject.AbstractModule
import com.rpg.entity.animate.player.{PlayerAction, PlayerAnimation}
import com.rpg.entity.item.projectiles.ProjectileSystem
import com.rpg.entity.item.projectiles.projectile_systems.{GhostFireballSystem, ProjectileMoveService}
import com.rpg.game.config.{CurrentGameConfigurationHelper, CurrentMasterConfig}
import com.rpg.game.systems.ListenerSystem
import com.rpg.game.systems.physics.Remover
import com.rpg.game.systems.physics.world.{PhysicsObjectConsumer, PhysicsObjectProducer, PhysicsObjectService}
import com.rpg.game.systems.rendering.RenderSystem
import com.rpg.game.systems.rendering.services.gameobjects.GameObjectCache
import com.rpg.game.systems.tick.{TickListener, TickSystem}

import scala.reflect.ClassTag

class GameModule(tickSystem: TickSystem, renderSystem: RenderSystem, currentMasterConfig: CurrentMasterConfig) extends AbstractModule{

  override def configure(): Unit = {

    //World
    bind(classOf[CurrentMasterConfig]).toInstance(currentMasterConfig)
    //bind(classOf[PhysicsObjectConsumer]).toInstance(physicsObjectConsumer)
    bind(classOf[GameObjectCache]).asEagerSingleton()
    bind(classOf[PhysicsObjectService]).asEagerSingleton()
    bind(classOf[ProjectileMoveService]).asEagerSingleton()

    //RenderSystem (performs updates synchronously)
    bind(classOf[RenderSystem]).toInstance(renderSystem)
    //---Children of RenderSystem---
    //bind(classOf[CurrentSettingsHelper]).toInstance(new CurrentSettingsHelper(renderSystem, currentSettings))
    bind(classOf[Remover]).toInstance(new Remover(renderSystem,currentMasterConfig.gameSystemConfig.objectRenderingServiceHandler)) //TODO -> test if its better to have remover update async or sync


    //TickSystem (performs updates asynchronously)
    bind(classOf[TickSystem]).toInstance(tickSystem)
    //---Children of TickSystem---
    bind(classOf[PlayerAction]).asEagerSingleton()
    bind(classOf[GhostFireballSystem]).asEagerSingleton()
    bind(classOf[PlayerAnimation]).asEagerSingleton()



  }



}
