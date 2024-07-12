package com.rpg.game.systems.rendering.services

import com.badlogic.gdx.maps.objects.TextureMapObject
import com.rpg.game.cache.Cache
import com.rpg.game.cache.caches.MapBasedCache

import javax.inject.Singleton

@Singleton
case class GameObjectCache() extends MapBasedCache[String,GameObject] {
  
}
