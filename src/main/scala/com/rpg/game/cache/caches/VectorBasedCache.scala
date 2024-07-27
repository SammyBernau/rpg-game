package com.rpg.game.cache.caches

import com.rpg.game.cache.Cache

/**
 * Used to track a list of elements that needs to be handled FIFO
 * A vector is used as 'append' and 'head'/'tail' execute closer to constant time than a traditional Scala Queue
 * Source -> https://docs.scala-lang.org/overviews/collections-2.13/performance-characteristics.html
 *
 * @tparam A -> generic
 */

trait VectorBasedCache[A] extends Cache{

  protected var cache: Vector[A] = Vector.empty

  def add(item: A): Unit = synchronized {
    cache = cache :+ item
  }
  
  def remove(element: A): Unit = synchronized {
    cache = cache.filterNot(_ == element)
  }

  def getCache: Vector[A] = synchronized(cache)


}
