package com.rpg.game.screens

import com.artemis.ComponentMapper
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.AssetLoader
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.{GL20, OrthographicCamera}
import com.badlogic.gdx.graphics.g2d.{Sprite, TextureRegion}
import com.badlogic.gdx.graphics.g3d.Environment
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.{Application, Gdx, Input, InputAdapter, InputMultiplexer, Screen, ScreenAdapter}
import com.google.inject.Guice
import com.rpg.game.config.map.TiledMapConfig
import com.rpg.game.{GameModule, RPG}
import com.rpg.game.systems.cursor.CustomCursor
import com.rpg.game.systems.physics.collision.CollisionListener
import com.rpg.game.systems.physics.world.WorldService
import com.rpg.game.systems.rendering.RenderSystem
import com.rpg.game.systems.rendering.services.gameobjects.ObjectRenderingService
import com.rpg.game.systems.rendering.services.world.WorldRenderingService
import com.rpg.game.systems.tick.TickSystem


class GameScreen(game: RPG) extends ScreenAdapter {

  //Box2D.init() -> Either this has to be called or WorldService.world needs to be called before objectRenderingServiceHandler.parseObjectsFromMap() for box2d to work
  private val DELTA_TIME: Float = Gdx.graphics.getDeltaTime
  

  //Game settings
  private val mapName = "assets/Tiled/Grassland.tmx" //Will change later so its not hardcoded

  //Create injections for GameModule
  private val gameInjector = Guice.createInjector(new GameModule(mapName))
  
  private val worldService = gameInjector.getInstance(classOf[WorldService])
  private val world = gameInjector.getInstance(classOf[World])
  private val worldRenderingService = gameInjector.getInstance(classOf[WorldRenderingService])
  private val viewport = gameInjector.getInstance(classOf[TiledMapConfig]).viewport
  private val tickSystem = gameInjector.getInstance(classOf[TickSystem])
  private val renderSystem = gameInjector.getInstance(classOf[RenderSystem])
  private val objectRenderingService = gameInjector.getInstance(classOf[ObjectRenderingService])

  
  override def show(): Unit = {
    val collisionListener = new CollisionListener
    world.setContactListener(collisionListener)
    worldRenderingService.setDrawBodies(false)
    val cursor = new CustomCursor(viewport.getCamera, game.batch)
  }


  override def render(delta: Float): Unit = {
    Gdx.gl.glClearColor(0, 0, 0, 0) //MAKE SURE TO CLEAR SCREEN OR CHANGE BACKGROUND AS PREVIOUS SCREEN WILL STILL BE THERE. TOOK ME FOREVER TO FIND THIS OUT!
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

    //apply camera
    viewport.apply()

    //Update tick events
    tickSystem.render()
    tickSystem.updateListeners()

    //Update render events
    renderSystem.updateListeners()

    //ALL UPDATES MADE TO WORLD NEED TO BE CALLED BEFORE THIS (ie: creation, moving, updating of physic entities)
    worldService.stepWorld(DELTA_TIME)

    //render and camera
    objectRenderingService.setView(viewport.getCamera.asInstanceOf[OrthographicCamera])
    objectRenderingService.render()
    worldRenderingService.render(world, viewport.getCamera.combined)

    game.batch.begin()
    game.font.draw(game.batch, s"Tick: ${tickSystem.getCurrentTick}", Gdx.graphics.getWidth / 2.toFloat, 100)
    game.batch.end()
  }

  /**
   * Backup camera movement method
   */
  private def backupUpdateCamera(): Unit = {
    val w = Gdx.input.isKeyPressed(Input.Keys.W)
    val a = Gdx.input.isKeyPressed(Input.Keys.A)
    val s = Gdx.input.isKeyPressed(Input.Keys.S)
    val d = Gdx.input.isKeyPressed(Input.Keys.D)

    val shift = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)
    var speed = if (shift) 500f else 300f

    if ((w || s) && (a || d)) {
      speed = speed / Math.sqrt(2.0).toFloat
    }

    val camera = viewport.getCamera

    if (w) camera.position.y = camera.position.y + speed * DELTA_TIME
    if (a) camera.position.x = camera.position.x - speed * DELTA_TIME
    if (d) camera.position.x = camera.position.x + speed * DELTA_TIME
    if (s) camera.position.y = camera.position.y - speed * DELTA_TIME

    camera.update()
  }

  override def resize(width: Int, height: Int): Unit = {
    viewport.update(width, height, true)
  }

  override def dispose(): Unit = {
    game.batch.dispose()
    game.font.dispose()
  }


}
