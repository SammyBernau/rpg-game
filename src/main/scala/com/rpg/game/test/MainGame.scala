package com.rpg.game.test

import com.badlogic.gdx.graphics.g2d.{BitmapFont, SpriteBatch}
import com.badlogic.gdx.graphics.{GL20, Texture}
import com.badlogic.gdx.{Game, Gdx}

class MainGame extends Game {
  
  lazy val font = new BitmapFont()
  lazy val batch = new SpriteBatch
  
  
  override def create(): Unit = {
    val simpleScreen = new SimpleScreen(this)
    this.setScreen(simpleScreen)
  }

  override def render(): Unit = super.render()

  override def dispose(): Unit = {
    batch.dispose()
    font.dispose()
  }
}
