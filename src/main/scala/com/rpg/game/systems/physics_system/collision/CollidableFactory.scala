package com.rpg.game.systems.physics_system.collision

import com.badlogic.gdx.physics.box2d.Fixture
import com.rpg.entity.ObjectUserData
import com.rpg.game.systems.physics_system.collision.collidables.GhostFireballProjectileCollidable

object CollidableFactory {

  def getCollidable(fixtureA: Fixture, fixtureB: Fixture): Option[Collidable] = {
    val aUserData = fixtureA.getBody.getUserData.asInstanceOf[ObjectUserData]
    val bUserData = fixtureB.getBody.getUserData.asInstanceOf[ObjectUserData]

    //This is terrible i know but I will fix it once another object needs collisions handled which will probably be soon lol
    if (aUserData != null && aUserData.getObjName == "GhostFireball") Some(new GhostFireballProjectileCollidable(fixtureA))
    else if (bUserData != null && bUserData.getObjName == "GhostFireball") Some(new GhostFireballProjectileCollidable(fixtureB))
    else None
  }

}
