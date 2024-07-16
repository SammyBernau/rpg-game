package com.rpg.game.cache.caches

import com.rpg.game.cache.Cache

/**
 * Used to track a list of elements that needs to be handled FIFO
 * A vector is used as 'append' and 'head'/'tail' execute closer to constant time than a traditional Scala Queue
 * Source -> https://docs.scala-lang.org/overviews/collections-2.13/performance-characteristics.html
 *
 * @tparam Type -> generic
 */

//TODO -> Does it need to be a FIFO structure if requests will be added concurrently?
trait VectorBasedCache[Type] extends Cache{

  protected var cache: Vector[Type] = Vector.empty

  def add(item: Type): Unit = synchronized {
    cache = cache :+ item
  }
  
  def remove(element: Type): Unit = synchronized {
    cache = cache.filterNot(_ == element)
  }

  def getCache: Vector[Type] = synchronized(cache)


}
