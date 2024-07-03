package com.rpg.game.systems

import com.badlogic.gdx.ApplicationAdapter

trait Listener extends ApplicationAdapter{

  override def dispose(): Unit = super.dispose()
  override def create(): Unit = super.create()
  override def render(): Unit = super.render()
}
