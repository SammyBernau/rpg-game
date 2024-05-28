package com.rpg.game.game

import com.badlogic.gdx.{Gdx, Screen, ScreenAdapter}
import com.badlogic.gdx.graphics.{Color, GL20, OrthographicCamera}
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.{Actor, Stage}
import com.badlogic.gdx.scenes.scene2d.ui.{Dialog, Label, Skin, Table, TextButton}
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.{ScreenViewport, Viewport}
import com.rpg.game.RPG
import com.rpg.game.game.util.cursor.CustomCursor

class MainMenuScreen(game: RPG) extends ScreenAdapter {
  
  var stage: Stage = new Stage()
  private var atlas: TextureAtlas = _
  private var skin: Skin = _

  var camera = new OrthographicCamera()
  camera.setToOrtho(false, 1280, 720)
  private var gameScreen: GameScreen = _
  


  override def show(): Unit = {
    stage = new Stage(new ScreenViewport())
    Gdx.input.setInputProcessor(stage)

    atlas = new TextureAtlas(Gdx.files.internal("ui/vhsui/vhs-ui.atlas"))
    skin = new Skin(Gdx.files.internal("ui/vhsui/vhs-ui.json"))

    val rootTable = new Table()
    rootTable.setFillParent(true)
    stage.addActor(rootTable)

    val titleLabel = new Label("Tavern Adventure",skin,"play")
    rootTable.add(titleLabel).expandX().left().padLeft(25.0f).padTop(25.0f)

    rootTable.row()
    val subTable = new Table()
    subTable.defaults().left()
    rootTable.add(subTable)

    val mainMenuLabel = new Label("- M E N U - ",skin)
    subTable.add(mainMenuLabel).padTop(50.0f).center()

    subTable.row()

    //main menu buttons
    val singleplayerButton = new TextButton("Singleplayer",skin)
    singleplayerButton.addListener(new ChangeListener {
      override def changed(event: ChangeListener.ChangeEvent, actor: Actor): Unit = {
        gameScreen = new GameScreen(game)
        game.getScreen.dispose()
        game.setScreen(gameScreen)
      }
    })
    
    subTable.add(singleplayerButton).padTop(35.0f)

    subTable.row()
    val multiplayerButton = new TextButton("Multiplayer",skin)
    multiplayerButton.addListener(new ChangeListener {
      override def changed(event: ChangeListener.ChangeEvent, actor: Actor): Unit = {
        comingSoon("Multiplayer")
      }
    })
    subTable.add(multiplayerButton).padTop(35.0f)

    subTable.row()
    val settingsButton = new TextButton("Settings",skin)
    settingsButton.addListener(new ChangeListener {
      override def changed(event: ChangeListener.ChangeEvent, actor: Actor): Unit = {
        comingSoon("Settings")
      }
    })
    subTable.add(settingsButton).padTop(35.0f)
  }

  private def comingSoon(text: String): Unit = {
    val dialog = new Dialog(text,skin)
    dialog.text("Coming soon!")
    dialog.button("Ok",skin)
    dialog.show(stage)
  }

  override def render(delta: Float): Unit = {
    Gdx.gl.glClearColor(0, 0, 0, 0)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    camera.update()

    stage.act()
    stage.draw()
  }

  override def resize(width: Int, height: Int): Unit = {stage.getViewport.update(width,height,true)}

  override def pause(): Unit = {}

  override def resume(): Unit = {}

  override def hide(): Unit = {
  }

  override def dispose(): Unit = {
    skin.dispose()
    atlas.dispose()
    stage.dispose()
  }

}
