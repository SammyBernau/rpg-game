package com.rpg.game.systems.physics.collision

import com.badlogic.gdx.physics.box2d.Fixture
import com.rpg.game.systems.physics.collision.collidables.GhostFireballProjectileCollidable
import com.rpg.game.systems.physics.world.ObjectData

object CollidableFactory {

  def getCollidable(fixtureA: Fixture, fixtureB: Fixture): Option[Collidable] = {
    val aUserData = fixtureA.getBody.getUserData.asInstanceOf[ObjectData]
    val bUserData = fixtureB.getBody.getUserData.asInstanceOf[ObjectData]

    val aId = aUserData.getId
    val bId = bUserData.getId
    println(s"aId: ${aId}")
    println(s"bId: ${bId}")

    //This is terrible i know but I will fix it once another object needs collisions handled which will probably be soon lol
    if (aUserData.getId != null && aUserData.getId.contains("ghost_fireball")) Some(new GhostFireballProjectileCollidable(fixtureA))
    else if (bUserData.getId != null && bUserData.getId.contains("ghost_fireball")) Some(new GhostFireballProjectileCollidable(fixtureB))
    else None
  }

}
