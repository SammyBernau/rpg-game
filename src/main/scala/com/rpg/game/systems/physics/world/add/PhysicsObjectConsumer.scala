package com.rpg.game.systems.physics.world.add

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.{Body, BodyDef, Fixture, FixtureDef, Joint, JointDef, World}
import com.rpg.game.structure.Consumer
import com.rpg.game.systems.physics.world.ObjectBody
import com.rpg.game.systems.rendering.{RenderListener, RenderSystem}
import com.rpg.game.systems.rendering.services.gameobjects.{GameObject, GameObjectCache}

import javax.inject.{Inject, Singleton}

@Singleton
class PhysicsObjectConsumer @Inject(renderSystem: RenderSystem, world: World, gameObjectCache: GameObjectCache, physicsObjectService: PhysicsObjectService) extends Consumer with RenderListener{
  
  renderSystem.addListener(this)

  override def renderListener(): Unit = {
    consume()
  }
  
//  val world = new World(new Vector2(0,0),true)
//
//  def stepWorld(deltaTime: Float): Unit = world.step(deltaTime,6,2)

  private def createObjectBody(bodyDef: BodyDef): ObjectBody = ObjectBody(world.createBody(bodyDef))

  private def createFixture(body: Body, fixtureDef: FixtureDef): Fixture = body.createFixture(fixtureDef)

  private def createFixtures(body: Body, fixtureDefs : FixtureDef*): Array[Fixture] = {
    val fixtures = new Array[Fixture](fixtureDefs.length)
    
    for(i <- fixtureDefs.indices){
      val currentFixtureDef = fixtureDefs(i)
      fixtures(i) = body.createFixture(currentFixtureDef)
    }
    fixtures
  }
  
  private def createJoint(jointDef: JointDef): Joint = world.createJoint(jointDef)

  override def consume(): Unit = synchronized {
    if (physicsObjectService.getCache.nonEmpty) {
      physicsObjectService.getCache.foreach { physicsObject =>
        val shape = physicsObject.shape
        val mapObject = physicsObject.mapObject
        val bodyDef = physicsObject.body
        val maybeFixtureDef = physicsObject.maybeFixtureDef
        val objectData = physicsObject.objectData


        if(objectData.getId == null) objectData.id = "generic_static_object"

        val objectBody = createObjectBody(bodyDef)
        objectBody.setObjectData(objectData)

        maybeFixtureDef match {
          case Some(fixtureDef) =>
            val fixture = createFixture(objectBody.body, fixtureDef)
            val newGameObject = GameObject(mapObject, fixture)
            gameObjectCache.add(mapObject.getName, newGameObject)
          case None =>
            val fixture = objectBody.body.createFixture(shape, 0.0f)
            val newGameObject = GameObject(mapObject, fixture)
            gameObjectCache.add(mapObject.getName, newGameObject)
        }
        //Fulfill request by removing it from request list
        shape.dispose()
        physicsObjectService.remove(physicsObject)
      }
    }
  }
}
