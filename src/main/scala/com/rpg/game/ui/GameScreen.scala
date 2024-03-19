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
import com.badlogic.gdx.maps.MapLayer
import com.badlogic.gdx.maps.objects.RectangleMapObject
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.maps.tiled.{TiledMap, TmxMapLoader}
import com.badlogic.gdx.math.{Rectangle, Vector2}
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.physics.box2d.{BodyDef, Box2DDebugRenderer, CircleShape, FixtureDef, PolygonShape, Transform, World}
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
import com.rpg.game.entity.OrthogonalTiledMapRendererWithObjects
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
  private var tiledRenderer: OrthogonalTiledMapRendererWithObjects = _
  private var map: TiledMap = _
  private var entityLayer: MapLayer = _

  //box2d testing
  private var world: World = _
  private val debugRenderer = new Box2DDebugRenderer()


  /**Tiled Implementation*/
  override def show(): Unit = {
    //will load all entities including player via one method later. Testing for now
    val player = Player(10, "test", "test", Owner,
      Humanoid("smallballs", 54, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 150f, 50f, true, 0, 0, BaseHumanoidEquipmentSetup(None, None, None, None, None, None, None, None, None)))

    map = new TmxMapLoader().load("assets/Tiled/Grassland.tmx")
    tiledRenderer = new OrthogonalTiledMapRendererWithObjects(map)

    camera = new OrthographicCamera()
    camera.setToOrtho(false, 600, 300)
    camera.position.set(770,787,0)

    //tiledRenderer.setView(camera)
    viewport = new ExtendViewport(600, 600, camera)


    //box2d testing
    //https://libgdx.com/wiki/extensions/physics/box2d
    world = new World(new Vector2(0,-10),true)


    //define player physics
    val playerBodyDef = new BodyDef()
    playerBodyDef.`type` = BodyType.DynamicBody
    playerBodyDef.position.set(player.playerSettings.x,player.playerSettings.y)
    val playerBody = world.createBody(playerBodyDef)


    val playerCircle = new CircleShape()

    val playerFixtureDef = new FixtureDef
    playerFixtureDef.shape = playerCircle
    playerFixtureDef.density = 0.5f
    playerFixtureDef.friction = 0.4f
    playerFixtureDef.restitution = 0.0f

    val playerFixture = playerBody.createFixture(playerFixtureDef)

    //define ground physics
    val groundBodyDef = new BodyDef
    groundBodyDef.position.set(new Vector2(0,10))

    val groundBody = world.createBody(groundBodyDef)
    val groundBox = new PolygonShape()
    groundBox.setAsBox(camera.viewportWidth,10.0f)
    groundBody.createFixture(groundBox,0.0f)










  }


  /** Tiled Implementation */
  override def render(delta: Float): Unit = {
    Gdx.gl.glClearColor(0, 0, 0, 0) //MAKE SURE TO CLEAR SCREEN OR CHANGE BACKGROUND AS PREVIOUS SCREEN WILL STILL BE THERE. TOOK ME FOREVER TO FIND THIS OUT!
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

    tiledRenderer.render()
    tiledRenderer.setView(camera)

    world.step(1/60f, 6,2)
    debugRenderer.render(world,camera.combined)

    updateCamera()
    updateCameraZoom()

    updateCamera()
    game.batch.begin()

    game.batch.end()
  }

  private def updateCamera(): Unit = {
    val w = Gdx.input.isKeyPressed(Input.Keys.W)
    val a = Gdx.input.isKeyPressed(Input.Keys.A)
    val s = Gdx.input.isKeyPressed(Input.Keys.S)
    val d = Gdx.input.isKeyPressed(Input.Keys.D)

    val shift = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)
    var speed = if (shift) 250f else 100f

    if ((w || s) && (a || d)) {
      speed = speed / Math.sqrt(2.0).toFloat
    }


    var playerEntity = tiledRenderer.getTextureMapObject("player_entity")

    if (w) playerEntity.setY(playerEntity.getY + (speed * DELTA_TIME)) //up
    if (s) playerEntity.setY(playerEntity.getY - (speed * DELTA_TIME)) //down
    if (a) playerEntity.setX(playerEntity.getX - (speed * DELTA_TIME)) //left
    if (d) playerEntity.setX(playerEntity.getX + (speed * DELTA_TIME)) //right

    camera.position.set(playerEntity.getX,playerEntity.getY,0)
    camera.update()
  }

  private def updateCameraZoom(): Unit = {
    val up = Gdx.input.isKeyPressed(Input.Keys.UP)
    val down = Gdx.input.isKeyPressed(Input.Keys.DOWN)

    if(up) camera.zoom = camera.zoom - .01f
    if(down) camera.zoom = camera.zoom + .01f

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
