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
import com.rpg.entity.animate.entityconstructs.Humanoid
import com.rpg.entity.animate.player
import com.rpg.entity.animate.player.{Owner, Player, PlayerAction, PlayerAnimation}
import com.rpg.entity.item.equipment.BaseHumanoidEquipmentSetup
import com.rpg.entity.item.projectiles.GhostFireball
import com.rpg.game.RPG
import com.rpg.game.config.CurrentSettings
import com.rpg.game.systems.cursor_system.{CursorBehavior, CustomCursor}
import com.rpg.game.systems.physics_system.Remover
import com.rpg.game.systems.physics_system.World.WORLD
import com.rpg.game.systems.physics_system.collision.CollisionListener
import com.rpg.game.systems.rendering_system.RendererWithObjects
import com.rpg.game.systems.tick_system.{Tick, TickListener}


class GameScreen(game: RPG) extends ScreenAdapter {

  private var currentSettings: CurrentSettings = _
  private var playerAction: PlayerAction = _
  private var playerAnimation: PlayerAnimation = _
  private var cursor: CustomCursor = _
  private var ghostFireball: GhostFireball = _
  private var remover: Remover = _
  
  //Create Delta Time
  private val DELTA_TIME: Float = Gdx.graphics.getDeltaTime
  //Create tick system
  private val tickSystem = new Tick()


  override def show(): Unit = {
    val map = new TmxMapLoader().load("assets/Tiled/Grassland.tmx")
    val mapRenderer = new RendererWithObjects(map)
    val tileSize = map.getLayers.get(0).asInstanceOf[TiledMapTileLayer].getTileWidth
    val viewport = new ExtendViewport((30 * tileSize).toFloat, (20 * tileSize).toFloat)
    val collisionListener = new CollisionListener
    remover = new Remover(mapRenderer)
    WORLD.setContactListener(collisionListener)
    

    currentSettings = CurrentSettings(viewport, mapRenderer, map, new Box2DDebugRenderer())
    mapRenderer.parseObjectsFromMap()
    currentSettings.worldRenderer.setDrawBodies(true)
    playerAnimation = new PlayerAnimation(currentSettings,tickSystem)
    playerAction = new PlayerAction(currentSettings)


    ghostFireball = new GhostFireball(currentSettings,tickSystem)
    cursor = new CustomCursor(currentSettings,game.batch)
  }


  override def render(delta: Float): Unit = {
    Gdx.gl.glClearColor(0, 0, 0, 0) //MAKE SURE TO CLEAR SCREEN OR CHANGE BACKGROUND AS PREVIOUS SCREEN WILL STILL BE THERE. TOOK ME FOREVER TO FIND THIS OUT!
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

    currentSettings.viewport.apply()
    remover.removeBodySafely()

    WORLD.step(DELTA_TIME, 6,2)

    currentSettings.mapRenderer.setView(currentSettings.viewport.getCamera.asInstanceOf[OrthographicCamera])
    currentSettings.mapRenderer.render()

    currentSettings.worldRenderer.render(WORLD,currentSettings.viewport.getCamera.combined)
    tickSystem.render()


    playerAction.playerMovement(playerAnimation.isDodging)
    playerAnimation.update(tickSystem.getCurrentTick)
    playerAction.playerCameraZoom()

    ghostFireball.update(tickSystem.getCurrentTick)

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

    val camera = currentSettings.viewport.getCamera

    if (w) camera.position.y = camera.position.y + speed * DELTA_TIME
    if (a) camera.position.x = camera.position.x - speed * DELTA_TIME
    if (d) camera.position.x = camera.position.x + speed * DELTA_TIME
    if (s) camera.position.y = camera.position.y - speed * DELTA_TIME

    camera.update()
  }

  override def resize(width: Int, height: Int): Unit = {
    currentSettings.viewport.update(width,height,true)
  }

  override def dispose(): Unit = {
    game.batch.dispose()
    game.font.dispose()
    currentSettings.worldRenderer.dispose()
    currentSettings.mapRenderer.dispose()
    currentSettings.tiledMap.dispose()
  }


}
