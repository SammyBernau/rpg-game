package com.rpg.game.ui

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.{Sprite, TextureRegion}
import com.badlogic.gdx.{Gdx, Input, Screen}
import com.badlogic.gdx.graphics.{GL20, OrthographicCamera, Texture}
import com.badlogic.gdx.math.Rectangle
import com.rpg.game.RPG
import com.rpg.game.config.GameConfig
import com.rpg.game.config.GameConfig.World.worldWidth
import com.rpg.game.entity.animate.Humanoid
import com.rpg.game.entity.item.equipment.BaseHumanoidEquipmentSetup
import com.rpg.game.entity.skins.EntitySkins
import games.rednblack.editor.renderer.{SceneConfiguration, SceneLoader}
import games.rednblack.editor.renderer.resources.{AsyncResourceManager, ResourceManagerLoader}



class GameScreen(game: RPG) extends Screen {
  //testing
  private val DELTA_TIME: Float = Gdx.graphics.getDeltaTime
  private var playerRect: Rectangle = _
  private var camera: OrthographicCamera = _
  private var background: Sprite = _
  private var testPlayerSprite: TextureRegion = _
  private var sceneLoader: SceneLoader = _
  
  private var stateTime: Float = 0f

  //for now hardcoding it but should make a function that loads all entities
  var player: Humanoid = _
  var equipment: BaseHumanoidEquipmentSetup = _

  //DONT DRAW TEXTURES INDIVIDUALLY!
  // costs a lot of memory and resources that are unnecessary when you load its texture by itself
  // use a texture region


  //TODO refactor most of this. Was testing systems before.
  override def show(): Unit = {
    //will load all entities including player via one method later. Testing for now
    equipment = BaseHumanoidEquipmentSetup(None, None, None, None, None, None, None, None, None)
    player = Humanoid("smallballs", 54, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 50, true, 0, 0, equipment)


    //sets starting playerSprite
    testPlayerSprite = EntitySkins.Player.front

    background = new Sprite(new Texture(Gdx.files.internal("bambooForestSpriteBackground.jpg")))
    background.setPosition(0, 0)
    background.setSize(GameConfig.World.worldWidth.toFloat, GameConfig.World.worldHeight.toFloat)

    //camera settings
    val w = Gdx.graphics.getWidth
    val h = Gdx.graphics.getHeight
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
    
    
    //hyperlab 2 stuff
    val assetManager = new AssetManager()
    assetManager.setLoader(AsyncResourceManager.getClass,new ResourceManagerLoader(assetManager.getFileHandleResolver))
    assetManager.load(Gdx.files.internal("testScene/project.dt"), AsyncResourceManager.getClass)
    
    assetManager.finishLoading()
    
    val asyncResourceManager = assetManager.get(Gdx.files.internal("testScene/project.dt"),AsyncResourceManager().getClass)
    
    val config = new SceneConfiguration()
    config.setResourceRetriever(asyncResourceManager)
    
    sceneLoader = new SceneLoader(config)
  }

  override def render(delta: Float): Unit = {
    Gdx.gl.glClearColor(0, 0, 0, 0) //MAKE SURE TO CLEAR SCREEN OR CHANGE BACKGROUND AS PREVIOUS SCREEN WILL STILL BE THERE. TOOK ME FOREVER TO FIND THIS OUT!
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    stateTime = stateTime + DELTA_TIME

    handleInput()

    camera.update()
    
    sceneLoader.getEngine().process()

    game.batch.setProjectionMatrix(camera.combined)
    
    game.batch.begin()
    game.batch.draw(background, background.getX, background.getY, background.getWidth, background.getHeight)
    game.batch.draw(testPlayerSprite, playerRect.x, playerRect.y, playerRect.width, playerRect.width)
    game.batch.end()
  }

  private def handleInput(): Unit = {
    //character movement
    if (Gdx.input.isKeyPressed(Input.Keys.W)) {
      //divide since walking too fast on y axis
      playerRect.y = playerRect.y + (player.walkingSpeed/2 * DELTA_TIME).toFloat
      testPlayerSprite = EntitySkins.Player.backAnimation.getKeyFrame(stateTime, true)
    } //up
    if (Gdx.input.isKeyPressed(Input.Keys.S)) {
      playerRect.y = playerRect.y - (player.walkingSpeed/2 * DELTA_TIME).toFloat
      testPlayerSprite = EntitySkins.Player.frontAnimation.getKeyFrame(stateTime, true)
    } //down
    if (Gdx.input.isKeyPressed(Input.Keys.A)) {
      playerRect.x = playerRect.x - (player.walkingSpeed * DELTA_TIME).toFloat
      testPlayerSprite = EntitySkins.Player.leftAnimation.getKeyFrame(stateTime, true)
    } //left
    if (Gdx.input.isKeyPressed(Input.Keys.D)) {
      playerRect.x = playerRect.x + (player.walkingSpeed * DELTA_TIME).toFloat
      testPlayerSprite = EntitySkins.Player.rightAnimation.getKeyFrame(stateTime, true)
    } //right

    updateCameraToPlayer()
  }

  private def updateCameraToPlayer(): Unit = {
    val middleOfPlayerX = playerRect.x + (playerRect.width / 4)
    val middleOfPlayerY = playerRect.y + (playerRect.height / 4)
    camera.position.set(middleOfPlayerX, middleOfPlayerY, 0)
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
