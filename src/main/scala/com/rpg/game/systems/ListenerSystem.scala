package com.rpg.game.systems

import com.badlogic.gdx.ApplicationAdapter

trait ListenerSystem[Type] extends Listener {

  var listeners: List[Type] = List.empty

  def addListener(listener: Type): Unit = synchronized {
    listeners = listener :: listeners
  }

  def removeListener(listener: Type): Unit = synchronized {
    listeners = listeners.filterNot(_ == listener)
  }

  override def dispose(): Unit = {
    listeners = List.empty
  }

  def updateListeners(): Unit
}
