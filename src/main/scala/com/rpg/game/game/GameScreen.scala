package com.rpg.game.game

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
import com.badlogic.gdx.physics.box2d.{BodyDef, Box2D, Box2DDebugRenderer, CircleShape, Fixture, FixtureDef, PolygonShape, Shape, Transform, World}
import com.badlogic.gdx.utils.viewport.{ExtendViewport, Viewport}
import com.rpg.game.RPG
import com.rpg.game.entity.animate.{Humanoid, player}
import com.rpg.game.entity.animate.player.{Owner, Player, PlayerAction, PlayerAnimation}
import com.rpg.game.entity.item.equipment.BaseHumanoidEquipmentSetup
import com.rpg.game.entity.item.equipment.projectile.GhostFireball
import games.rednblack.editor.renderer.{ExternalTypesConfiguration, SceneConfiguration, SceneLoader}
import games.rednblack.editor.renderer.resources.{AsyncResourceManager, ResourceManagerLoader}
import com.rpg.game.game.config.{CurrentWorld, GameConfig}
import com.rpg.game.game.config.GameConfig.GameWorld.WORLD
import com.rpg.game.game.util.cursor.{CursorBehavior, CustomCursor}
import com.rpg.game.game.util.rendering.OrthogonalTiledMapRendererWithObjects
import com.rpg.game.ticksystem.{Tick, TickListener}


class GameScreen(game: RPG) extends ScreenAdapter {

  private val DELTA_TIME: Float = Gdx.graphics.getDeltaTime
  private var currentWorld: CurrentWorld = _
  private var playerAction: PlayerAction = _
  private var playerAnimation: PlayerAnimation = _
  private var cursor: CustomCursor = _
  private val tickSystem = new Tick()
  private var ghostFireball: GhostFireball = _


  override def show(): Unit = {
    val map = new TmxMapLoader().load("assets/Tiled/Grassland.tmx")
    val mapRenderer = new OrthogonalTiledMapRendererWithObjects(map)
    val tileSize = map.getLayers.get(0).asInstanceOf[TiledMapTileLayer].getTileWidth
    val viewport = new ExtendViewport((30 * tileSize).toFloat, (20 * tileSize).toFloat)



    currentWorld = CurrentWorld(viewport, mapRenderer, map, new Box2DDebugRenderer())
    mapRenderer.parseObjectsFromMap()
    currentWorld.worldRenderer.setDrawBodies(true)
    playerAnimation = new PlayerAnimation(currentWorld,tickSystem)
    playerAction = new PlayerAction(currentWorld)


    ghostFireball = new GhostFireball(currentWorld)
    cursor = new CustomCursor(currentWorld,game.batch)
  }


  override def render(delta: Float): Unit = {
    Gdx.gl.glClearColor(0, 0, 0, 0) //MAKE SURE TO CLEAR SCREEN OR CHANGE BACKGROUND AS PREVIOUS SCREEN WILL STILL BE THERE. TOOK ME FOREVER TO FIND THIS OUT!
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

    currentWorld.viewport.apply()
    WORLD.step(DELTA_TIME, 6,2)

    currentWorld.mapRenderer.setView(currentWorld.viewport.getCamera.asInstanceOf[OrthographicCamera])
    currentWorld.mapRenderer.render()

    currentWorld.worldRenderer.render(WORLD,currentWorld.viewport.getCamera.combined)
    tickSystem.render()


    playerAction.playerMovement(playerAnimation.isDodging)
    playerAnimation.update(tickSystem.getCurrentTick)
    playerAction.playerCameraZoom()

    ghostFireball.create()

    //cursor.set()
    game.batch.begin()
    game.font.draw(game.batch,s"Tick: ${tickSystem.getCurrentTick}", Gdx.graphics.getWidth/2.toFloat, 100)

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

    val camera = currentWorld.viewport.getCamera

    if (w) camera.position.y = camera.position.y + speed * DELTA_TIME
    if (a) camera.position.x = camera.position.x - speed * DELTA_TIME
    if (d) camera.position.x = camera.position.x + speed * DELTA_TIME
    if (s) camera.position.y = camera.position.y - speed * DELTA_TIME

    camera.update()
  }

  override def resize(width: Int, height: Int): Unit = {
    currentWorld.viewport.update(width,height,true)
  }

  override def dispose(): Unit = {
    game.batch.dispose()
    game.font.dispose()
    currentWorld.worldRenderer.dispose()
    currentWorld.mapRenderer.dispose()
    currentWorld.tiledMap.dispose()
  }


}
