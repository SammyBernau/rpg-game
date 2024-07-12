package com.rpg.game.systems.physics.world

import com.rpg.game.cache.caches.VectorBasedCache

import javax.inject.Singleton


@Singleton
case class PhysicsObjectService() extends VectorBasedCache[PhysicsObjectDefWrapper] {
  
}
