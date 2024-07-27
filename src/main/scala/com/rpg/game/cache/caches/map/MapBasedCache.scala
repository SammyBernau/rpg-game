package com.rpg.game.cache.caches.map

import com.rpg.game.cache.Cache

trait MapBasedCache[K,V] extends Cache {
  protected var cache: Map[K,V] = Map.empty
  
  def add(key: K, value: V): Unit = cache = cache + (key -> value)
  def get(key: K): Option[V] = cache.get(key)
  def getOrElse(key: K, other: V): V = cache.getOrElse(key,other)
  def remove(key: K): Unit = cache = cache.removed(key)
  def getCache: Map[K,V] = cache
}
