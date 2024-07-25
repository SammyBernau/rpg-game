package com.rpg.game.cache.caches.map

import com.rpg.game.cache.Cache

trait MapBasedCache[Key,Value] extends Cache {
  protected var cache: Map[Key,Value] = Map.empty
  
  def add(key: Key, value: Value): Unit = cache = cache + (key -> value)
  def get(key: Key): Option[Value] = cache.get(key)
  def getOrElse(key: Key, other: Value): Value = cache.getOrElse(key,other)
  def remove(key: Key): Unit = cache = cache.removed(key)
  def getCache: Map[Key,Value] = cache
}
