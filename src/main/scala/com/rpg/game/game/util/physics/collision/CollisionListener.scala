package com.rpg.game.game.util.physics.collision

import com.badlogic.gdx.physics.box2d.*

class CollisionListener extends ContactListener{

  override def beginContact(contact: Contact): Unit = {
    val fixtureA = contact.getFixtureA
    val fixtureB = contact.getFixtureB

    handleCollision(fixtureA,fixtureB)
  }

  override def endContact(contact: Contact): Unit = {

  }

  override def postSolve(contact: Contact, impulse: ContactImpulse): Unit = {

  }

  override def preSolve(contact: Contact, oldManifold: Manifold): Unit = {

  }

  private def handleCollision(fixtureA: Fixture, fixtureB: Fixture): Unit = { 
    CollidableFactory.getCollidable(fixtureA, fixtureB).foreach(_.handleCollision())
  }


}
