package com.rpg.game.systems.rendering

//RenderEvent
trait RenderEvent {
  
  protected val renderSystem: RenderSystem
  renderSystem.addEvent(this)
  //Render
  def render(): Unit

}
