package com.rpg.game.systems.physics.world

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.{Body, BodyDef, Fixture, FixtureDef, Joint, JointDef, World}
import com.rpg.game.systems.physics.World.WORLD

import javax.inject.{Inject, Singleton}

@Singleton
class PhysicsObjectConsumer @Inject()(physicsObjectService: PhysicsObjectService) {
  //TODO -> Add finalized physics objects to GameObjectCache
  private val world = new World(new Vector2(0,0),true)

  def stepWorld(deltaTime: Float): Unit = world.step(deltaTime,6,2)

  def createBody(bodyDef: BodyDef): Body = world.createBody(bodyDef)

  def createFixture(fixtureDef: FixtureDef, body: Body): Fixture = body.createFixture(fixtureDef)

  def createFixtures(body: Body, fixtureDefs : FixtureDef*): Array[Fixture] = {
    val fixtures = new Array[Fixture](fixtureDefs.length)
    
    for(i <- fixtureDefs.indices){
      val currentFixtureDef = fixtureDefs(i)
      fixtures(i) = body.createFixture(currentFixtureDef)
    }
    fixtures
  }
  
  def createJoint(jointDef: JointDef): Joint = world.createJoint(jointDef)
  
}
