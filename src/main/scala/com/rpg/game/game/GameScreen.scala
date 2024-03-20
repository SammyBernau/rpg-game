package com.rpg.game.game

import com.artemis.ComponentMapper
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.AssetLoader
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.g2d.{Sprite, TextureRegion}
import com.badlogic.gdx.graphics.g3d.Environment
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute
import com.badlogic.gdx.{Application, Gdx, Input, InputAdapter, InputMultiplexer, Screen, ScreenAdapter}
import com.badlogic.gdx.graphics.{Color, GL20, OrthographicCamera, Texture}
import com.badlogic.gdx.maps.MapLayer
import com.badlogic.gdx.maps.objects.RectangleMapObject
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.maps.tiled.{TiledMap, TiledMapTileLayer, TmxMapLoader}
import com.badlogic.gdx.math.{Rectangle, Vector2}
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.physics.box2d.{BodyDef, Box2D, Box2DDebugRenderer, CircleShape, Fixture, FixtureDef, PolygonShape, Shape, Transform, World}
import com.badlogic.gdx.utils.viewport.{ExtendViewport, Viewport}
import com.rpg.game.RPG
import com.rpg.game.entity.animate.Humanoid
import com.rpg.game.entity.animate.player.{Owner, Player, PlayerAnimation, PlayerComponent, PlayerScript}
import com.rpg.game.entity.item.equipment.BaseHumanoidEquipmentSetup
import com.rpg.game.entity.textures.TextureGrabber
import com.rpg.game.ticksystem.Tick
import games.rednblack.editor.renderer.{ExternalTypesConfiguration, SceneConfiguration, SceneLoader}
import games.rednblack.editor.renderer.resources.{AsyncResourceManager, ResourceManagerLoader}
import com.badlogic.gdx.scenes.scene2d.actions.Actions.*
import com.fasterxml.jackson.databind.jsontype.DefaultBaseTypeLimitingValidator
import com.rpg.game.game.config.GameConfig
import com.rpg.game.game.config.GameConfig.GameWorld.WORLD
import com.rpg.game.game.systems.CameraSystem


class GameScreen(game: RPG) extends ScreenAdapter {

  private val DELTA_TIME: Float = Gdx.graphics.getDeltaTime

  private var viewport: Viewport = _
  private var mapRenderer: OrthogonalTiledMapRenderer = _
  private var map: TiledMap = _
  private var entityLayer: MapLayer = _

  private val worldRenderer = new Box2DDebugRenderer()
  private var playerFixture: Fixture = _
  private var player: Player = _
  private var camera: OrthographicCamera = new OrthographicCamera()


  override def show(): Unit = {
    player = Player(10, "test", "test", Owner,
      Humanoid("smallballs", 54, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 20000f, 10000f, true, 770,787, BaseHumanoidEquipmentSetup(None, None, None, None, None, None, None, None, None)))

    map = new TmxMapLoader().load("assets/Tiled/Grassland.tmx")
    mapRenderer = new OrthogonalTiledMapRenderer(map)
    val tileSize = map.getLayers.get(0).asInstanceOf[TiledMapTileLayer].getTileWidth


    viewport = new ExtendViewport((30 * tileSize).toFloat, (20 * tileSize).toFloat)



    //box2d testing
    //https://libgdx.com/wiki/extensions/physics/box2d

    //define player physics
    val playerBodyDef = new BodyDef()
    playerBodyDef.`type` = BodyType.DynamicBody
    playerBodyDef.position.set(player.playerSettings.x,player.playerSettings.y)
    playerBodyDef.linearDamping = 1f
    val playerBody = WORLD.createBody(playerBodyDef)


    val playerShape = new PolygonShape()
    playerShape.setAsBox((8),(12.5))

    val playerFixtureDef = new FixtureDef
    playerFixtureDef.shape = playerShape
    playerFixtureDef.density = 0.5f
    playerFixtureDef.friction = 1f
    playerFixtureDef.restitution = 0.0f

    playerFixtureDef.shape

    playerFixture = playerBody.createFixture(playerFixtureDef)

//    //define ground physics
//    val groundBodyDef = new BodyDef
//    groundBodyDef.position.set(new Vector2(0,10))
//
//    val groundBody = world.createBody(groundBodyDef)
//    val groundBox = new PolygonShape()
//    groundBox.setAsBox(camera.viewportWidth,10.0f)
//    groundBody.createFixture(groundBox,0.0f)
  }


  override def render(delta: Float): Unit = {
    Gdx.gl.glClearColor(0, 0, 0, 0) //MAKE SURE TO CLEAR SCREEN OR CHANGE BACKGROUND AS PREVIOUS SCREEN WILL STILL BE THERE. TOOK ME FOREVER TO FIND THIS OUT!
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)


    WORLD.step(DELTA_TIME, 6,2)


    mapRenderer.setView(viewport.getCamera.asInstanceOf[OrthographicCamera])
    mapRenderer.render()

    worldRenderer.render(WORLD,viewport.getCamera.combined)

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
    var speed = if (shift) player.playerSettings.sprintingSpeed else player.playerSettings.walkingSpeed

    if ((w || s) && (a || d)) {
      speed = speed / Math.sqrt(2.0).toFloat
    }

    var x = 0f
    var y = 0f

//    val playerVelocity = playerFixture.getBody.getLinearVelocity
//    val playerPosition = playerFixture.getBody.getPosition
    val (width,height) = getPolygonShapeDimensions(playerFixture.getShape)

    //val playerEntity = tiledRenderer.getTextureMapObject("player_entity")

//    if (w && playerVelocity.y < 500f) playerFixture.getBody.applyLinearImpulse(0, 50.0f, playerPosition.x, playerPosition.y,true)
//    if (a && playerVelocity.x > -500f) playerFixture.getBody.applyLinearImpulse(-50.0f, 0, playerPosition.x, playerPosition.y, true)
//    if (d && playerVelocity.x < 500f) playerFixture.getBody.applyLinearImpulse(50.0f, 0, playerPosition.x, playerPosition.y, true)
//    if (s && playerVelocity.y > -500f) playerFixture.getBody.applyLinearImpulse(0, -50.0f, playerPosition.x, playerPosition.y,true)

    if (w) y = y + speed * DELTA_TIME
    if (a) x = x - speed * DELTA_TIME
    if (d) x = x + speed * DELTA_TIME
    if (s) y = y - speed * DELTA_TIME

    playerFixture.getBody.setLinearVelocity(x, y)

    viewport.getCamera.position.set(playerFixture.getBody.getPosition.x,playerFixture.getBody.getPosition.y,0)
    //playerEntity.setX(playerFixture.getBody.getPosition.x - (width/2))
    //playerEntity.setY(playerFixture.getBody.getPosition.y - (height/2))

    viewport.getCamera.update()
  }

  private def getPolygonShapeDimensions(shape: Shape): (Float, Float) = {
    val poly = shape.asInstanceOf[PolygonShape]

    val vertices = Array.fill(poly.getVertexCount)(new Vector2())

    for (i <- 0 until poly.getVertexCount) {
      poly.getVertex(i, vertices(i))
    }

    val minX = vertices.minBy(_.x).x
    val maxX = vertices.maxBy(_.x).x
    val minY = vertices.minBy(_.y).y
    val maxY = vertices.maxBy(_.y).y

    val width = maxX - minX
    val height = maxY - minY

    (width,height)

  }

  private def updateCameraZoom(): Unit = {
    val up = Gdx.input.isKeyPressed(Input.Keys.UP)
    val down = Gdx.input.isKeyPressed(Input.Keys.DOWN)

    if(up) camera.zoom = camera.zoom - .01f
    if(down) camera.zoom = camera.zoom + .01f

    camera.update()
  }


  override def resize(width: Int, height: Int): Unit = {
    viewport.update(width,height,true)
  }

  override def dispose(): Unit = {
  }


}
