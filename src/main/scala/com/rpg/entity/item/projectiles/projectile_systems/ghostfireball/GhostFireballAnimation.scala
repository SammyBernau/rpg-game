package com.rpg.entity.item.projectiles.projectile_systems.ghostfireball

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.maps.objects.TextureMapObject
import com.rpg.entity.textures.EntityAnimations
import com.rpg.game.config.map.TiledMapConfig
import com.rpg.game.systems.rendering.gameobjects.GameObjectCache
import com.rpg.game.systems.rendering.{RenderEvent, RenderSystem}
import com.rpg.game.systems.tick.{TickEvent, TickSystem}

import javax.inject.Inject

final class GhostFireballAnimation @Inject(tiledMapConfig: TiledMapConfig,
                                           gameObjectCache: GameObjectCache,
                                           val renderSystem: RenderSystem,
                                           val tickSystem: TickSystem) extends RenderEvent {

  private val entityAnimations = EntityAnimations(tiledMapConfig)
  private val ghostFireballAnimation = entityAnimations.GhostFireBall.animation

  def deltaTime: Float = Gdx.graphics.getDeltaTime

  override def render(): Unit = {
    animate(tickSystem.getCurrentTick)
  }

  def animate(tick: Long): Unit = {
    gameObjectCache.getCache.foreach { (name,gamObject) =>
      if(name != null) {
        if (name.contains("ghost_fireball")) {
          val textureMapObject = gamObject.mapObject.asInstanceOf[TextureMapObject]
          textureMapObject.setTextureRegion(ghostFireballAnimation.getKeyFrame(tick, true))
        }
      }
    }
  }




}
