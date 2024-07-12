package com.rpg.game.systems.physics.world

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.{Body, BodyDef, Fixture, FixtureDef, Joint, JointDef, World}
import com.rpg.game.systems.physics.World.WORLD
import com.rpg.game.systems.rendering.services.{GameObject, GameObjectCache}

import javax.inject.{Inject, Singleton}

@Singleton
class PhysicsObjectConsumer @Inject()(gameObjectCache: GameObjectCache, physicsObjectService: PhysicsObjectService) {
  //TODO -> Add finalized physics objects to GameObjectCache
  private val world = new World(new Vector2(0,0),true)

  def stepWorld(deltaTime: Float): Unit = world.step(deltaTime,6,2)

  private def createBody(bodyDef: BodyDef): Body = world.createBody(bodyDef)

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

  def consume(): Unit = {
    physicsObjectService.getCache.foreach{physicsObject =>
      val shape = physicsObject.shape
      val mapObject = physicsObject.mapObject
      val bodyDef = physicsObject.body
      val maybeFixtureDef = physicsObject.maybeFixtureDef
      val objectUserData = physicsObject.objectUserData

      val body = createBody(bodyDef)
      body.setUserData(objectUserData)

      maybeFixtureDef match {
        case Some(fixtureDef) =>
          val fixture = createFixture(body,fixtureDef)
          val newGameObject = GameObject(mapObject, fixture)
          gameObjectCache.add(newGameObject)
        case None =>
          body.createFixture(shape,0.0f)
      }
    }
  }
  
}