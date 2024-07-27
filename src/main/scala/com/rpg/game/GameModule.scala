package com.rpg.game

import com.badlogic.gdx.physics.box2d.World
import com.google.inject.AbstractModule
import com.rpg.entity.animate.player.{PlayerAnimation, PlayerCameraZoom, PlayerMovement}
import com.rpg.entity.item.projectiles.ProjectileSystem
import com.rpg.entity.item.projectiles.projectile_systems.{GhostFireballSystem, ProjectileMoveCache, ProjectileMoveConsumer}
import com.rpg.game.config.map.{TiledMapConfig, TiledMapConfigService}
import com.rpg.game.systems.EventSystem
import com.rpg.game.systems.physics.world.WorldService
import com.rpg.game.systems.physics.world.add.{PhysicsObjectCache, PhysicsObjectConsumer, PhysicsObjectProducer}
import com.rpg.game.systems.physics.world.remove.{RemoveObjectCache, RemoveObjectConsumer, RemoveObjectProducer}
import com.rpg.game.systems.rendering.RenderSystem
import com.rpg.game.systems.rendering.services.gameobjects.{GameObjectCache, ObjectRenderingService, ObjectRenderingServiceHandler}
import com.rpg.game.systems.rendering.services.world.WorldRenderingService
import com.rpg.game.systems.tick.{TickEvent, TickSystem}

import scala.reflect.ClassTag

class GameModule(mapName: String) extends AbstractModule{

  private val worldService = new WorldService()
  private val tiledMapConfig = new TiledMapConfigService(mapName).loadConfig()

  override def configure(): Unit = {

    // World/Map
    bind(classOf[TiledMapConfig]).toInstance(tiledMapConfig)
    bind(classOf[WorldService]).toInstance(worldService)
    bind(classOf[World]).toInstance(worldService.world)

    //Systems
    bind(classOf[TickSystem])
    bind(classOf[RenderSystem])

    //Caches
    bind(classOf[ProjectileMoveCache])
    bind(classOf[RemoveObjectCache])
    bind(classOf[GameObjectCache])
    bind(classOf[PhysicsObjectCache])

    //Producers
    bind(classOf[PhysicsObjectProducer])
    bind(classOf[RemoveObjectProducer])
    bind(classOf[RemoveObjectProducer])

    //Services
    bind(classOf[WorldRenderingService])
    bind(classOf[ObjectRenderingService])
    bind(classOf[ObjectRenderingServiceHandler]).asEagerSingleton() //maybe make it singleton?

    //Consumers
    bind(classOf[PhysicsObjectConsumer]).asEagerSingleton()
    bind(classOf[RemoveObjectConsumer])
    bind(classOf[ProjectileMoveConsumer])

    //---Children of RenderSystem---
    //bind(classOf[CurrentSettingsHelper]).toInstance(new CurrentSettingsHelper(renderSystem, currentSettings))


    //---Children of TickSystem---
    bind(classOf[PlayerMovement]).asEagerSingleton()
    bind(classOf[GhostFireballSystem]).asEagerSingleton()
    bind(classOf[PlayerAnimation]).asEagerSingleton()
    bind(classOf[PlayerCameraZoom]).asEagerSingleton()



  }



}
