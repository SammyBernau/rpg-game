package com.rpg.game.game.util.physics.collision

import com.badlogic.gdx.physics.box2d.Fixture
import com.rpg.game.entity.UserData
import com.rpg.game.game.util.physics.collision.collidables.GhostFireballProjectileCollidable

object CollidableFactory {

  def getCollidable(fixtureA: Fixture, fixtureB: Fixture): Option[Collidable] = {
    val aUserData = fixtureA.getBody.getUserData.asInstanceOf[UserData]
    val bUserData = fixtureB.getBody.getUserData.asInstanceOf[UserData]

    if (aUserData != null && aUserData.objName == "GhostFireball") Some(new GhostFireballProjectileCollidable(fixtureA))
    else if (bUserData != null && bUserData.objName == "GhostFireball") Some(new GhostFireballProjectileCollidable(fixtureB))
    else None

  }

}