package com.rpg.game.ui

import com.badlogic.gdx.{ApplicationListener, Gdx}
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.{GL20, OrthographicCamera}
import com.badlogic.gdx.utils.viewport.Viewport
import games.rednblack.editor.renderer.{SceneConfiguration, SceneLoader}
import games.rednblack.editor.renderer.resources.AsyncResourceManager
import com.badlogic.gdx.utils.viewport.ExtendViewport

class HyperlabTest extends ApplicationListener{

  private val assetManagerCreator = new MyAssetManager
  private var assetManager: AssetManager = _
  private var sceneLoader: SceneLoader = _
  private var asyncResourceManager: AsyncResourceManager = _
  private var viewport: Viewport = _
  private var camera: OrthographicCamera = _
  private var engine: com.artemis.World = _




  override def create(): Unit = {
    assetManager = assetManagerCreator.getAssetManager
    assetManager.load("project.dt",AsyncResourceManager().getClass)

    assetManager.finishLoading()

    asyncResourceManager = assetManager.get("project.dt", AsyncResourceManager().getClass)

    val config = new SceneConfiguration()
    config.setResourceRetriever(asyncResourceManager)

    sceneLoader = new SceneLoader(config)
    engine = sceneLoader.getEngine

    camera = new OrthographicCamera()

    viewport = new ExtendViewport(15,8,camera)
    sceneLoader.loadScene("MainScene", viewport)

    


  }

  override def render(): Unit = {
    Gdx.gl.glClearColor(0, 0, 0, 0)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    
    viewport.apply()
    engine.process()
    

  }

  override def resume(): Unit = {

  }

  override def pause(): Unit = {

  }

  override def dispose(): Unit = {

  }

  override def resize(width: Int, height: Int): Unit = {

  }

}
