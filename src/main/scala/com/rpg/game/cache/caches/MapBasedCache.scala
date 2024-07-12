package com.rpg.game.cache.caches

import com.rpg.game.cache.Cache

trait MapBasedCache[Key,Value] extends Cache[Value] {
  private var cache = Map.empty[Key,Value]
  
  def add(key: Key, value: Value): Unit = cache = cache + (key -> value)
  def get(key: Key): Option[Value] = cache.get(key)
  def remove(key: Key): Unit = cache = cache.removed(key)
  
  def getCache: Map[Key,Value] = cache
}
