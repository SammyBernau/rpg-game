package com.rpg.game.systems.physics.collision

import com.badlogic.gdx.physics.box2d.Fixture
import com.rpg.entity.ObjectUserData
import com.rpg.game.systems.physics.collision.collidables.GhostFireballProjectileCollidable

object CollidableFactory {

  def getCollidable(fixtureA: Fixture, fixtureB: Fixture): Option[Collidable] = {
    val aUserData = fixtureA.getBody.getUserData.asInstanceOf[ObjectUserData]
    val bUserData = fixtureB.getBody.getUserData.asInstanceOf[ObjectUserData]

    //This is terrible i know but I will fix it once another object needs collisions handled which will probably be soon lol
    if (aUserData != null && aUserData.getId != null) {
      if(aUserData.getId.contains("ghost_fireball")) {
        return Some(new GhostFireballProjectileCollidable(fixtureA))
      }
      None
    } else if (bUserData != null && bUserData.getId != null) {
      if (bUserData.getId.contains("ghost_fireball")) {
        return Some(new GhostFireballProjectileCollidable(fixtureB))
      }
      None
    } else None
  }

}
