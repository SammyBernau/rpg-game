package com.rpg.game.game.util.physics.collision

import com.badlogic.gdx.physics.box2d.Fixture
import com.rpg.game.entity.UserData
import com.rpg.game.game.util.physics.collision.collidables.GhostFireballProjectileCollidable

object CollidableFactory {

  def getCollidable(fixtureA: Fixture, fixtureB: Fixture): Option[Collidable] = {
    val aUserData = fixtureA.getBody.getUserData.asInstanceOf[UserData]
    val bUserData = fixtureB.getBody.getUserData.asInstanceOf[UserData]


    //This is terrible i know but I will fix it once another object needs collisions handled which will probably be soon lol
    if (aUserData != null && aUserData.getObjName == "GhostFireball") Some(new GhostFireballProjectileCollidable(fixtureA))
    else if (bUserData != null && bUserData.getObjName == "GhostFireball") Some(new GhostFireballProjectileCollidable(fixtureB))
    else if ((aUserData != null && aUserData.getObjName == "GhostFireball") && (bUserData != null && bUserData.getObjName == "GhostFireball")) None
    else None

  }

}
