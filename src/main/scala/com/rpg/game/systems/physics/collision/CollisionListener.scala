package com.rpg.game.systems.physics.collision

import com.badlogic.gdx.physics.box2d.*
import com.rpg.game.systems.physics.world.ObjectData

class CollisionListener extends ContactListener{

  override def beginContact(contact: Contact): Unit = {
    val fixtureA = contact.getFixtureA
    val fixtureB = contact.getFixtureB

    val aUserData = fixtureA.getBody.getUserData.asInstanceOf[ObjectData]
    if (aUserData != null) {
      aUserData.print()
    }
    val bUserData = fixtureB.getBody.getUserData.asInstanceOf[ObjectData]
    if (bUserData != null) {
      bUserData.print()
    }
    
    //TODO -> two collision events are being detected for every 'real' collision
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
