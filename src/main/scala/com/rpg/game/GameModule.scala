package com.rpg.game

import com.google.inject.AbstractModule
import com.rpg.entity.animate.player.{PlayerAction, PlayerAnimation}
import com.rpg.entity.item.projectiles.ProjectileSystem
import com.rpg.entity.item.projectiles.projectile_systems.GhostFireballSystem
import com.rpg.game.config.CurrentSettings
import com.rpg.game.systems.ListenerSystem
import com.rpg.game.systems.physics_system.Remover
import com.rpg.game.systems.rendering_system.RenderSystem
import com.rpg.game.systems.tick_system.{TickListener, TickSystem}

import scala.reflect.ClassTag

class GameModule(tickSystem: TickSystem, renderSystem: RenderSystem, currentSettings: CurrentSettings) extends AbstractModule{

  override def configure(): Unit = {
    //Systems
    bind(classOf[TickSystem]).toInstance(tickSystem)
    bind(classOf[RenderSystem]).toInstance(renderSystem)

    //Physics
    bind(classOf[Remover]).toInstance(new Remover(renderSystem,currentSettings.mapRenderer))
    
    //World
    bind(classOf[CurrentSettings]).toInstance(currentSettings)

    //Player Actions
    bind(classOf[GhostFireballSystem]).asEagerSingleton()
    bind(classOf[PlayerAnimation]).asEagerSingleton()
    bind(classOf[PlayerAction]).asEagerSingleton()


  }



}
