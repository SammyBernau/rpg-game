package com.rpg.game.systems.physics_system.collision.collidables

import com.badlogic.gdx.physics.box2d.Fixture
import com.rpg.entity.ObjectUserData
import com.rpg.game.systems.physics_system.collision.Collidable

class GhostFireballProjectileCollidable(fixtureA: Fixture) extends Collidable {
  override def handleCollision(): Unit = {
    //set delete tag on fireball to be destroyed
    val userData = fixtureA.getBody.getUserData.asInstanceOf[ObjectUserData]
    userData.isFlaggedForDelete = true
  }
}
