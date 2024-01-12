package com.rpg.game.ui

import com.badlogic.gdx.{ApplicationAdapter, Gdx}
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.utils.viewport.{ScreenViewport, Viewport}
import com.rpg.game.RPG

class MainMenuStage(game: RPG) extends ApplicationAdapter {

  private val stage = new Stage()
  private val camera = new OrthographicCamera()
  private val atlas = new TextureAtlas(Gdx.files.internal("metalui/metal-ui.atlas"))
  private val skin = new Skin(Gdx.files.internal("metalui/metal-ui.json"))


  override def create(): Unit = {
    stage.setViewport(new ScreenViewport())
    Gdx.input.setInputProcessor(stage)


  }

  override def render(): Unit = {}

  override def resize(width: Int, height: Int): Unit = super.resize(width, height)

  override def dispose(): Unit = super.dispose()


}
