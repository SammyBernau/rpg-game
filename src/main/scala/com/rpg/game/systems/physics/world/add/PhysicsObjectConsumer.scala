package com.rpg.game.systems.physics.world.add

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.{Body, BodyDef, Fixture, FixtureDef, Joint, JointDef, World}
import com.rpg.game.structure.Consumer
import com.rpg.game.systems.physics.world.ObjectBody
import com.rpg.game.systems.rendering.gameobjects.{GameObject, GameObjectCache}
import com.rpg.game.systems.rendering.{RenderEvent, RenderSystem}

import javax.inject.{Inject, Singleton}

@Singleton
class PhysicsObjectConsumer @Inject(val renderSystem: RenderSystem,
                                    world: World,
                                    gameObjectCache: GameObjectCache,
                                    physicsObjectCache: PhysicsObjectCache) extends Consumer with RenderEvent {

  consume()

  override def render(): Unit = {
    consume()
  }


  private def createObjectBody(bodyDef: BodyDef): ObjectBody = ObjectBody(world.createBody(bodyDef))

  private def createFixture(body: Body, fixtureDef: FixtureDef): Fixture = body.createFixture(fixtureDef)

  private def createFixtures(body: Body, fixtureDefs: FixtureDef*): Array[Fixture] = {
    val fixtures = new Array[Fixture](fixtureDefs.length)

    for (i <- fixtureDefs.indices) {
      val currentFixtureDef = fixtureDefs(i)
      fixtures(i) = body.createFixture(currentFixtureDef)
    }
    fixtures
  }

  private def createJoint(jointDef: JointDef): Joint = world.createJoint(jointDef)

  override def consume(): Unit = {
    physicsObjectCache.getCache.foreach { physicsObject =>
      val shape = physicsObject.shape
      val mapObject = physicsObject.mapObject
      val bodyDef = physicsObject.body
      val maybeFixtureDef = physicsObject.maybeFixtureDef
      val objectData = physicsObject.objectData

      objectData.id = Option(objectData.getId).getOrElse("generic_static_object")

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
      physicsObjectCache.remove(physicsObject)
    }
  }
}
