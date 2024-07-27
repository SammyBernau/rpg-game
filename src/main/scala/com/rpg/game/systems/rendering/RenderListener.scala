package com.rpg.game.systems.rendering

//RenderEvent
trait RenderListener {
  
  val renderSystem: RenderSystem
  renderSystem.addListener(this)
  //Render
  def renderListener(): Unit

}
