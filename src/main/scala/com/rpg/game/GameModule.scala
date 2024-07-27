package com.rpg.game

import com.badlogic.gdx.physics.box2d.World
import com.google.inject.AbstractModule
import com.rpg.entity.animate.player.{PlayerMovement, PlayerAnimation, PlayerCameraZoom}
import com.rpg.entity.item.projectiles.ProjectileSystem
import com.rpg.entity.item.projectiles.projectile_systems.{GhostFireballSystem, ProjectileMoveConsumer, ProjectileMoveCache}
import com.rpg.game.config.{CurrentGameConfigurationHelper, CurrentMasterConfig}
import com.rpg.game.systems.EventSystem
import com.rpg.game.systems.physics.world.add.{PhysicsObjectConsumer, PhysicsObjectCache}
import com.rpg.game.systems.physics.world.remove.{RemoveObjectConsumer, RemoveObjectCache}
import com.rpg.game.systems.rendering.RenderSystem
import com.rpg.game.systems.rendering.services.gameobjects.GameObjectCache
import com.rpg.game.systems.tick.{TickEvent, TickSystem}

import scala.reflect.ClassTag

class GameModule(currentMasterConfig: CurrentMasterConfig) extends AbstractModule{

  override def configure(): Unit = {



    //Game systems and caches
    bind(classOf[CurrentMasterConfig]).toInstance(currentMasterConfig)
    bind(classOf[GameObjectCache]).asEagerSingleton()
    bind(classOf[PhysicsObjectCache]).asEagerSingleton()
    bind(classOf[ProjectileMoveCache]).asEagerSingleton()
    bind(classOf[RemoveObjectCache]).asEagerSingleton()

    //RenderSystem (performs updates synchronously)
    bind(classOf[RenderSystem]).toInstance(currentMasterConfig.gameSystemConfig.renderSystem)
    //---Children of RenderSystem---
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
