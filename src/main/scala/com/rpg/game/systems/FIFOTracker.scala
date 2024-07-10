package com.rpg.game.systems

/**
 * Used to track a list of elements that needs to be handled FIFO
 * A vector is used as 'append' and 'head'/'tail' execute closer to constant time than a traditional Scala Queue
 * Source -> https://docs.scala-lang.org/overviews/collections-2.13/performance-characteristics.html
 * @tparam Type -> generic
 */

//TODO -> Does it need to be a FIFO structure if requests will be added concurrently?
class FIFOTracker[Type] {

  private var tracker: Vector[Type] = Vector.empty

  def append(item: Type): Unit = synchronized {
    tracker = tracker :+ item
  }
  
  def getHead: Type = synchronized (tracker.head)

  def removeHead(): Unit = synchronized(if(tracker.nonEmpty) tracker = tracker.tail)

  def getFIFOTracker: Vector[Type] = synchronized(tracker)


}
