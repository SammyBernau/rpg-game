package com.rpg.game.systems

import com.badlogic.gdx.ApplicationAdapter

protected trait EventSystem[A] extends Event {

  var events: List[A] = List.empty

  def addEvent(event: A): Unit = synchronized {
    events = event :: events
  }

  def removeEvent(event: A): Unit = synchronized {
    events = events.filterNot(_ == event)
  }

  override def dispose(): Unit = {
    events = List.empty
  }
  
  def updateEvents(): Unit
  
}
