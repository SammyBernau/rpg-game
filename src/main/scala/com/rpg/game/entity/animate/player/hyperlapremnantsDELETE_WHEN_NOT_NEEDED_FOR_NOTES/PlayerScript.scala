package com.rpg.game.entity.animate.player.hyperlapremnantsDELETE_WHEN_NOT_NEEDED_FOR_NOTES

import com.artemis.ComponentMapper
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.physics.box2d.{Body, Contact, Fixture}
import com.badlogic.gdx.{Gdx, Input}
import com.rpg.game.entity.animate.player.Player
import games.rednblack.editor.renderer.components.physics.PhysicsBodyComponent
import games.rednblack.editor.renderer.components.{DimensionsComponent, MainItemComponent, TransformComponent}
import games.rednblack.editor.renderer.physics.PhysicsContact
import games.rednblack.editor.renderer.scripts.BasicScript
import games.rednblack.editor.renderer.utils.{ComponentRetriever, ItemWrapper}

class PlayerScript(playerLight: ItemWrapper, deltaTime: Float, player:Player) extends BasicScript, PhysicsContact {

  private var engine: com.artemis.World = _
  private var transformMapper: ComponentMapper[TransformComponent] = _
  private var mainItemMapper: ComponentMapper[MainItemComponent] = _
  private var dimensionsMapper: ComponentMapper[DimensionsComponent] = _
  private var physicsMapper: ComponentMapper[PhysicsBodyComponent] = _
  
  private var physicsBodyComponent: PhysicsBodyComponent = _

  override def init(item: Int): Unit = {
    super.init(item)

//    val itemWrapper = new ItemWrapper(item, engine)
//    animEntity = itemWrapper.getChild("player_anim").getEntity

    physicsBodyComponent = physicsMapper.get(item)
  }


  override def act(delta: Float): Unit = {
    movePlayer()
  }

  /**
   * Handles input for player movement
   */
  private def movePlayer(): Unit = {
    val w = Gdx.input.isKeyPressed(Input.Keys.W)
    val a = Gdx.input.isKeyPressed(Input.Keys.A)
    val s = Gdx.input.isKeyPressed(Input.Keys.S)
    val d = Gdx.input.isKeyPressed(Input.Keys.D)

    val body = physicsBodyComponent.body
    body.setFixedRotation(true)

    val shift = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)
    var speed = if (shift) player.playerSettings.sprintingSpeed else player.playerSettings.walkingSpeed

    if ((w || s) && (a || d)) {
      speed = speed / Math.sqrt(2.0).toFloat
    }

    var x = 0f
    var y = 0f

    if(w) y = y + speed
    if(a) x = x - speed
    if(d) x = x + speed
    if(s) y = y - speed

    body.setLinearVelocity(x,y)
    //updatePlayerLight()
  }


  /**
   * Updates light to position of player body
   */
  private def updatePlayerLight(): Unit = {
    if(physicsBodyComponent.body != null) {
      val body = physicsBodyComponent.body
      val playerLightTransform = playerLight.getComponent(classOf[TransformComponent])
      playerLightTransform.x = body.getPosition.x
      playerLightTransform.y = body.getPosition.y
    }
  }




  override def dispose(): Unit = {}

  override def beginContact(contactEntity: Int, contactFixture: Fixture, ownFixture: Fixture, contact: Contact): Unit = {}

  override def endContact(contactEntity: Int, contactFixture: Fixture, ownFixture: Fixture, contact: Contact): Unit = {}

  override def preSolve(contactEntity: Int, contactFixture: Fixture, ownFixture: Fixture, contact: Contact): Unit = {
    val transformComponent = transformMapper.get(this.entity)
    val colliderTransform = transformMapper.get(contactEntity)
    val colliderDimension = dimensionsMapper.get(contactEntity)
    if (transformComponent.y < colliderTransform.y + colliderDimension.height) contact.setFriction(0)
    else contact.setFriction(1)
  }

  override def postSolve(contactEntity: Int, contactFixture: Fixture, ownFixture: Fixture, contact: Contact): Unit = {}


}
