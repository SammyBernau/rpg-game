package com.rpg.game.screens

import com.artemis.ComponentMapper
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.AssetLoader
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.g2d.{Sprite, TextureRegion}
import com.badlogic.gdx.graphics.g3d.Environment
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute
import com.badlogic.gdx.{Application, Gdx, Input, InputAdapter, InputMultiplexer, Screen, ScreenAdapter}
import com.badlogic.gdx.graphics.{Color, GL20, OrthographicCamera, Pixmap, Texture}
import com.badlogic.gdx.maps.MapLayer
import com.badlogic.gdx.maps.objects.{EllipseMapObject, PolygonMapObject, RectangleMapObject, TextureMapObject}
import com.badlogic.gdx.maps.tiled.{TiledMap, TiledMapTileLayer, TmxMapLoader}
import com.badlogic.gdx.math.{Rectangle, Vector2, Vector3}
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.physics.box2d.{BodyDef, Box2D, Box2DDebugRenderer, CircleShape, ContactListener, Fixture, FixtureDef, PolygonShape, Shape, Transform, World}
import com.badlogic.gdx.utils.viewport.{ExtendViewport, Viewport}
import com.google.inject.Guice
import com.rpg.entity.animate.entityconstructs.Humanoid
import com.rpg.entity.animate.player
import com.rpg.entity.animate.player.{Owner, Player, PlayerAction, PlayerAnimation}
import com.rpg.entity.item.equipment.BaseHumanoidEquipmentSetup
import com.rpg.entity.item.projectiles.projectile_systems.{GhostFireballSystem, ProjectileMoveConsumer, ProjectileMoveService}
import com.rpg.game.config.gamesystems.GameSystemsConfigService
import com.rpg.game.config.map.TiledMapConfigService
import com.rpg.game.{GameModule, RPG}
import com.rpg.game.config.CurrentMasterConfig
import com.rpg.game.systems.cursor.CustomCursor
import com.rpg.game.systems.physics.collision.CollisionListener
import com.rpg.game.systems.physics.world.WorldService
import com.rpg.game.systems.physics.world.add.PhysicsObjectConsumer
import com.rpg.game.systems.physics.world.remove.RemoveObjectConsumer
import com.rpg.game.systems.rendering.services.gameobjects.{GameObjectCache, ObjectRenderingService, ObjectRenderingServiceHandler}
import com.rpg.game.systems.rendering.RenderSystem
import com.rpg.game.systems.rendering.services.world.WorldRenderingService
import com.rpg.game.systems.tick.{TickListener, TickSystem}


class GameScreen(game: RPG) extends ScreenAdapter {

  //Box2D.init() -> Either this has to be called or WorldService.world needs to be called before objectRenderingServiceHandler.parseObjectsFromMap() for box2d to work
  private val DELTA_TIME: Float = Gdx.graphics.getDeltaTime

  //Init physics world
  private val worldService = new WorldService()
  private val world = worldService.world

  //Game settings
  private val mapName = "assets/Tiled/Grassland.tmx" //Will change later so its not hardcoded
  private val tiledMapConfig = new TiledMapConfigService(mapName).loadConfig()
  //Game util systems
  private val gameSystemsConfig = new GameSystemsConfigService(tiledMapConfig.tiledMap).loadConfig()
  private val renderSystem = gameSystemsConfig.renderSystem
  private val gameObjectCache = gameSystemsConfig.gameObjectCache

  //Init consumers
    //Note: Consumers have to be added in this order (RemoveObjectConsumer -> ProjectileMoveConsumer -> PhysicsObjectConsumer) so that PhysicsObjectConsumer is on top
    //TODO -> Switch RenderSystem to use a FIFO data structure
  private val removeObjectConsumer = new RemoveObjectConsumer(
    renderSystem,
    world,
    gameSystemsConfig.removeObjectService,
    gameSystemsConfig.objectRenderingServiceHandler)

  private val projectileMoveConsumer = new ProjectileMoveConsumer(
    renderSystem,
    gameObjectCache,
    gameSystemsConfig.projectileMoveService)

  private val physicsObjectConsumer = new PhysicsObjectConsumer(
    renderSystem,
    world,
    gameObjectCache,
    gameSystemsConfig.physicsObjectService)

  //Save configurations to be shared across files that need it
  private val currentMasterConfig = CurrentMasterConfig(tiledMapConfig, gameSystemsConfig)

  //Initial physics objects creation of preloaded objects from object layer of TiledMap
  private val objectRenderingServiceHandler = gameSystemsConfig.objectRenderingServiceHandler
  objectRenderingServiceHandler.parseObjectsFromMap()
  physicsObjectConsumer.consume() //THIS HAS TO BE CALLED BEFORE INJECTOR SINCE THINGS IN INJECTOR RELY ON THIS BEING CALLED

  //Create injections for GameModule
  Guice.createInjector(new GameModule(world, currentMasterConfig))

  override def show(): Unit = {
    val collisionListener = new CollisionListener
    world.setContactListener(collisionListener)
    gameSystemsConfig.worldRenderingService.setDrawBodies(false)
    val cursor = new CustomCursor(currentMasterConfig, game.batch)
  }


  override def render(delta: Float): Unit = {
    Gdx.gl.glClearColor(0, 0, 0, 0) //MAKE SURE TO CLEAR SCREEN OR CHANGE BACKGROUND AS PREVIOUS SCREEN WILL STILL BE THERE. TOOK ME FOREVER TO FIND THIS OUT!
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

    //apply camera
    tiledMapConfig.viewport.apply()

    //Update tick events
    gameSystemsConfig.tickSystem.render()
    gameSystemsConfig.tickSystem.updateListeners()

    //Update render events
    gameSystemsConfig.renderSystem.updateListeners()

    //ALL UPDATES MADE TO WORLD NEED TO BE CALLED BEFORE THIS (ie: creation, moving, updating of physic entities)
    worldService.stepWorld(DELTA_TIME)

    //render and camera
    gameSystemsConfig.objectRenderingService.setView(tiledMapConfig.viewport.getCamera.asInstanceOf[OrthographicCamera])
    gameSystemsConfig.objectRenderingService.render()
    gameSystemsConfig.worldRenderingService.render(world, tiledMapConfig.viewport.getCamera.combined)

    game.batch.begin()
    game.font.draw(game.batch, s"Tick: ${gameSystemsConfig.tickSystem.getCurrentTick}", Gdx.graphics.getWidth / 2.toFloat, 100)
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

    val camera = tiledMapConfig.viewport.getCamera

    if (w) camera.position.y = camera.position.y + speed * DELTA_TIME
    if (a) camera.position.x = camera.position.x - speed * DELTA_TIME
    if (d) camera.position.x = camera.position.x + speed * DELTA_TIME
    if (s) camera.position.y = camera.position.y - speed * DELTA_TIME

    camera.update()
  }

  override def resize(width: Int, height: Int): Unit = {
    tiledMapConfig.viewport.update(width, height, true)
  }

  override def dispose(): Unit = {
    game.batch.dispose()
    game.font.dispose()
    currentMasterConfig.dispose()
  }


}
