package com.rpg.game.cache.caches.map

trait MapObject[Key, Value] {
  
  val key: Key
  val value: Value

}
