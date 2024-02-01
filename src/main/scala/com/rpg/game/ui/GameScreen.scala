package com.rpg.game.ui

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.AssetLoader
import com.badlogic.gdx.graphics.g2d.{Sprite, TextureRegion}
import com.badlogic.gdx.graphics.g3d.Environment
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute
import com.badlogic.gdx.{Gdx, Input, Screen}
import com.badlogic.gdx.graphics.{Color, GL20, OrthographicCamera, Texture}
import com.badlogic.gdx.math.{Rectangle, Vector2}
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.utils.viewport.{ExtendViewport, Viewport}
import com.rpg.game.RPG
import com.rpg.game.config.GameConfig
import com.rpg.game.config.GameConfig.World.worldWidth
import com.rpg.game.entity.animate.Humanoid
import com.rpg.game.entity.item.equipment.BaseHumanoidEquipmentSetup
import com.rpg.game.entity.textures.TextureGrabber
import com.rpg.game.ticksystem.Tick
import games.rednblack.editor.renderer.{ExternalTypesConfiguration, SceneConfiguration, SceneLoader}
import games.rednblack.editor.renderer.resources.{AsyncResourceManager, ResourceManagerLoader}
import com.rpg.game.ui.MyAssetManager
import games.rednblack.editor.renderer.box2dLight.{PointLight, RayHandler}
import games.rednblack.editor.renderer.components.light.LightObjectComponent
import games.rednblack.editor.renderer.data.LightVO
import games.rednblack.editor.renderer.utils.{ComponentRetriever, ItemWrapper}



class GameScreen(game: RPG) extends Screen {

  private val DELTA_TIME: Float = Gdx.graphics.getDeltaTime
  private val tickSystem = new Tick()

  //player
  private var playerRect: Rectangle = _
  private var background: Sprite = _
  private var testPlayerSprite: TextureRegion = _
  private var camera: OrthographicCamera = _
  private var playerLight: LightObjectComponent = _
  private var myLight: PointLight = _
  private var rayHandler: RayHandler = _
  //for now hardcoding it but should make a function that loads all entities
  var player: Humanoid = _
  var equipment: BaseHumanoidEquipmentSetup = _
  //for player's animation
  private var stateTime: Float = 0f

  //display & scene
  private val assetManagerCreator = new MyAssetManager
  private var assetManager: AssetManager = _
  private var sceneLoader: SceneLoader = _
  private var asyncResourceManager: AsyncResourceManager = _
  private var viewport: Viewport = _
  private var engine: com.artemis.World = _
  override def show(): Unit = {
    //will load all entities including player via one method later. Testing for now
    equipment = BaseHumanoidEquipmentSetup(None, None, None, None, None, None, None, None, None)
    player = Humanoid("smallballs", 54, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 50, true, 0, 0, equipment)

    //sets starting playerSprite
    testPlayerSprite = TextureGrabber.PlayerSkin.front

    //camera settings
    camera = new OrthographicCamera(300, 50)
    camera.update()

    //player settings
    playerRect = new Rectangle()

    //set player position and size
    //save last location of player in humanoid object so that once its serialized its in JSON
    playerRect.x = player.x
    playerRect.y = player.y
    playerRect.width = testPlayerSprite.getRegionWidth.toFloat
    playerRect.height = testPlayerSprite.getRegionHeight.toFloat

    //scene
    assetManager = assetManagerCreator.getAssetManager
    assetManager.load("project.dt", AsyncResourceManager().getClass)
    assetManager.finishLoading()

    asyncResourceManager = assetManager.get("project.dt", AsyncResourceManager().getClass)

    val config = new SceneConfiguration()
    config.setResourceRetriever(asyncResourceManager)

    sceneLoader = new SceneLoader(config)
    engine = sceneLoader.getEngine
    camera = new OrthographicCamera()
    viewport = new ExtendViewport(600, 300, camera)
    sceneLoader.loadScene("MainScene",viewport)

    //get player's light
//    val root = new ItemWrapper(sceneLoader.getRoot,engine)
//    val playerLightEntity = root.getChild("player_light")
//    playerLight = playerLightEntity.getComponent(classOf[LightObjectComponent])

    myLight = new PointLight(sceneLoader.getRayHandler,200)
    myLight.setXray(true)
    val color = new Color()
    myLight.setColor(new Color(Color.RED))
    myLight.setDistance(30.0)




  }

  override def render(delta: Float): Unit = {
    Gdx.gl.glClearColor(0, 0, 0, 0) //MAKE SURE TO CLEAR SCREEN OR CHANGE BACKGROUND AS PREVIOUS SCREEN WILL STILL BE THERE. TOOK ME FOREVER TO FIND THIS OUT!
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    stateTime = stateTime + DELTA_TIME

    tickSystem.render()

    viewport.apply()
    engine.process()


    handleInput()

    game.batch.setProjectionMatrix(camera.combined)

    game.batch.begin()
    game.batch.draw(testPlayerSprite, playerRect.x, playerRect.y, playerRect.width, playerRect.width)
    game.batch.end()
  }

  private def handleInput(): Unit = {
    //regular movement
    val w = Gdx.input.isKeyPressed(Input.Keys.W)
    val s = Gdx.input.isKeyPressed(Input.Keys.S)
    val a = Gdx.input.isKeyPressed(Input.Keys.A)
    val d = Gdx.input.isKeyPressed(Input.Keys.D)

    //character running movement
    val isShiftPressed = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)
    var speed = if (isShiftPressed) player.sprintingSpeed else player.walkingSpeed

    if((w || s) && (a || d)) {
      speed = speed / Math.sqrt(2.0).toFloat
    }

    //character walk movement
    if (w) {
      //divide since walking too fast on y axis
      playerRect.y = playerRect.y + (speed  /2 * DELTA_TIME).toFloat
      testPlayerSprite = TextureGrabber.PlayerSkin.PlayerAnimation.backAnimation.getKeyFrame(stateTime, true)
    } //up

    if (s) {
      playerRect.y = playerRect.y - (speed  /2 * DELTA_TIME).toFloat
      testPlayerSprite = TextureGrabber.PlayerSkin.PlayerAnimation.frontAnimation.getKeyFrame(stateTime, true)
    } //down

    if (a) {
      playerRect.x = playerRect.x - (speed * DELTA_TIME).toFloat
      testPlayerSprite = TextureGrabber.PlayerSkin.PlayerAnimation.leftAnimation.getKeyFrame(stateTime, true)
    } //left

    if (d) {
      playerRect.x = playerRect.x + (speed * DELTA_TIME).toFloat
      testPlayerSprite = TextureGrabber.PlayerSkin.PlayerAnimation.rightAnimation.getKeyFrame(stateTime, true)
    } //right

    updateCameraToPlayer()
  }

  private def updateCameraToPlayer(): Unit = {
    //updates camera to follow player with sprite offset
    val middleOfPlayerX = playerRect.x + (playerRect.width / 4)
    val middleOfPlayerY = playerRect.y + (playerRect.height / 4)
    camera.position.set(middleOfPlayerX, middleOfPlayerY, 0)
    camera.update()

    myLight.setPosition(middleOfPlayerX,middleOfPlayerY)

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

  }


}
