package com.rpg.game

import com.badlogic.gdx.graphics.g2d.{BitmapFont, SpriteBatch}
import com.badlogic.gdx.graphics.{GL20, Texture}
import com.badlogic.gdx.{Game, Gdx}
import com.rpg.game.ui.MainMenuScreen
import com.rpg.game.test.SimpleScreen

class RPG extends Game {
  
  lazy val font = new BitmapFont()
  lazy val batch = new SpriteBatch
  
  
  override def create(): Unit = {
    val mainMenu = new MainMenuScreen(this)
    this.setScreen(mainMenu)
  }

  override def render(): Unit = super.render()

  override def dispose(): Unit = {
    batch.dispose()
    font.dispose()
  }
}
