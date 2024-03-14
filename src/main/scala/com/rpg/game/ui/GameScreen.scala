package com.rpg.game.ui

import com.artemis.ComponentMapper
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.AssetLoader
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.g2d.{Sprite, TextureRegion}
import com.badlogic.gdx.graphics.g3d.Environment
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute
import com.badlogic.gdx.{Application, Gdx, Input, InputAdapter, InputMultiplexer, Screen}
import com.badlogic.gdx.graphics.{Color, GL20, OrthographicCamera, Texture}
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.maps.tiled.{TiledMap, TmxMapLoader}
import com.badlogic.gdx.math.{Rectangle, Vector2}
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.physics.box2d.{BodyDef, CircleShape, FixtureDef, Transform, World}
import com.badlogic.gdx.utils.viewport.{ExtendViewport, Viewport}
import com.rpg.game.RPG
import com.rpg.game.config.GameConfig
import com.rpg.game.config.GameConfig.World.worldWidth
import com.rpg.game.entity.animate.Humanoid
import com.rpg.game.entity.animate.player.{Owner, Player, PlayerAnimation, PlayerComponent, PlayerScript}
import com.rpg.game.entity.item.equipment.BaseHumanoidEquipmentSetup
import com.rpg.game.entity.textures.TextureGrabber
import com.rpg.game.ticksystem.Tick
import games.rednblack.editor.renderer.{ExternalTypesConfiguration, SceneConfiguration, SceneLoader}
import games.rednblack.editor.renderer.resources.{AsyncResourceManager, ResourceManagerLoader}
import com.rpg.game.ui.MyAssetManager
import games.rednblack.editor.renderer.box2dLight.{PointLight, RayHandler}
import games.rednblack.editor.renderer.components.TransformComponent
import games.rednblack.editor.renderer.components.light.LightObjectComponent
import games.rednblack.editor.renderer.data.{CompositeItemVO, LightVO, PhysicsBodyDataVO, PolygonShapeVO, SpriteAnimationVO}
import games.rednblack.editor.renderer.utils.{ComponentRetriever, ItemWrapper}
import com.badlogic.gdx.scenes.scene2d.actions.Actions.*
import com.fasterxml.jackson.databind.jsontype.DefaultBaseTypeLimitingValidator
import com.rpg.game.ui.systems.CameraSystem
import games.rednblack.editor.renderer.components.physics.PhysicsBodyComponent
import games.rednblack.editor.renderer.systems.action.Actions


class GameScreen(game: RPG) extends Screen {

  private val DELTA_TIME: Float = Gdx.graphics.getDeltaTime

  //display & scene
  private var camera: OrthographicCamera = _
  private val assetManagerCreator = new MyAssetManager
  private var assetManager: AssetManager = _
  private var sceneLoader: SceneLoader = _
  private var asyncResourceManager: AsyncResourceManager = _
  private var viewport: Viewport = _
  private var engine: com.artemis.World = _
  private var tiledRenderer: OrthogonalTiledMapRenderer = _

  /**HyperLap 2D Implementation*/
//  override def show(): Unit = {
//    //will load all entities including player via one method later. Testing for now
//    val player = Player(10, "test", "test", Owner,
//      Humanoid("smallballs", 54, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 150f, 50f, true, 0, 0, BaseHumanoidEquipmentSetup(None, None, None, None, None, None, None, None, None)))
//
//    //grab master file for scenes
//    assetManager = assetManagerCreator.getAssetManager
//    assetManager.load("project.dt", AsyncResourceManager().getClass)
//    assetManager.finishLoading()
//    asyncResourceManager = assetManager.get("project.dt", AsyncResourceManager().getClass)
//
//    val config = new SceneConfiguration()
//    config.setResourceRetriever(asyncResourceManager)
//    val cameraSystem = new CameraSystem
//    config.addSystem(cameraSystem)
//    config.addSystem(new PlayerAnimation)
//
//    //loads scenes and entities
//    sceneLoader = new SceneLoader(config)
//    engine = sceneLoader.getEngine
//
//    ComponentRetriever.addMapper(classOf[PlayerComponent])
//
//    camera = new OrthographicCamera(300,50)
//    viewport = new ExtendViewport(600, 300, camera)
//    sceneLoader.loadScene("MainScene", viewport)
//
//    //get player_light from MainScene.dt
//    //TODO make a custom wrapper over ItemWrapper to easily grab coords of entities
//    val root = new ItemWrapper(sceneLoader.getRoot, engine)
//
//    val playerLightEntity = root.getChild("player_light")
//
//    val playerEntity = root.getChild("player_default")
//    ComponentRetriever.create(playerEntity.getChild("player_animation").getEntity,classOf[PlayerComponent],engine)
//    cameraSystem.setFocus(playerEntity.getEntity)
//
//    //set location of player to last know point
//    val playerTransform = playerEntity.getComponent(classOf[TransformComponent])
//    playerTransform.x = player.playerSettings.x
//    playerTransform.y = player.playerSettings.y
//
//    //add script to player
//    playerEntity.addScript(new PlayerScript(playerLightEntity,DELTA_TIME,player))
//  }

  /**HyperLap 2D Implementation*/
  //  override def render(delta: Float): Unit = {
  //    Gdx.gl.glClearColor(0, 0, 0, 0) //MAKE SURE TO CLEAR SCREEN OR CHANGE BACKGROUND AS PREVIOUS SCREEN WILL STILL BE THERE. TOOK ME FOREVER TO FIND THIS OUT!
  //    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
  //    stateTime = stateTime + DELTA_TIME
  //
  //    viewport.apply()
  //    engine.process()
  //
  //    game.batch.begin()
  //    game.batch.end()
  //  }


  /**Tiled Implementation*/
  override def show(): Unit = {
    //will load all entities including player via one method later. Testing for now
    val player = Player(10, "test", "test", Owner,
      Humanoid("smallballs", 54, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 150f, 50f, true, 0, 0, BaseHumanoidEquipmentSetup(None, None, None, None, None, None, None, None, None)))

//    val assetManager = new AssetManager()
//    assetManager.setLoader(classOf[TiledMap], new TmxMapLoader(new InternalFileHandleResolver()))
//    assetManager.load("Grassland.tmx",classOf[TiledMap])
//
//    val map = assetManager.get("Grassland.tmx")
    val map = new TmxMapLoader().load("assets/Tiled/Grassland.tmx")
    tiledRenderer = new OrthogonalTiledMapRenderer(map)

    camera = new OrthographicCamera()
    camera.setToOrtho(false, 800, 800)
    //tiledRenderer.setView(camera)
    viewport = new ExtendViewport(800, 500, camera)
  }


  /** Tiled Implementation */
  override def render(delta: Float): Unit = {
    Gdx.gl.glClearColor(0, 0, 0, 0) //MAKE SURE TO CLEAR SCREEN OR CHANGE BACKGROUND AS PREVIOUS SCREEN WILL STILL BE THERE. TOOK ME FOREVER TO FIND THIS OUT!
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)


//    updateCamera()

    tiledRenderer.render()
    tiledRenderer.setView(camera)

    updateCamera()
    game.batch.begin()
    game.batch.end()
  }

  private def updateCamera(): Unit = {
    val w = Gdx.input.isKeyPressed(Input.Keys.W)
    val a = Gdx.input.isKeyPressed(Input.Keys.A)
    val s = Gdx.input.isKeyPressed(Input.Keys.S)
    val d = Gdx.input.isKeyPressed(Input.Keys.D)

    var speed = 100f

    if ((w || s) && (a || d)) {
      speed = speed / Math.sqrt(2.0).toFloat
    }

    if (w) camera.position.y = camera.position.y + (speed * DELTA_TIME) //up
    if (s) camera.position.y = camera.position.y - (speed * DELTA_TIME) //down
    if (a) camera.position.x = camera.position.x - (speed * DELTA_TIME) //left
    if (d) camera.position.x = camera.position.x + (speed * DELTA_TIME) //right

    camera.update()
  }


  override def resize(width: Int, height: Int): Unit = {

  }

  override def pause(): Unit = {

  }

  override def resume(): Unit = {

  }

  override def hide(): Unit = {

  }

  override def dispose(): Unit = {
    sceneLoader.dispose()
    assetManager.dispose()
  }


}
