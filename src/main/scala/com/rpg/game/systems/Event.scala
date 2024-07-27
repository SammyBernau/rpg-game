package com.rpg.game.systems

import com.badlogic.gdx.ApplicationAdapter

protected trait Event extends ApplicationAdapter {

  override def dispose(): Unit = super.dispose()
  override def create(): Unit = super.create()
  override def render(): Unit = super.render()
}
