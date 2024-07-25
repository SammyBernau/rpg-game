package com.rpg.game.systems.physics.collision.collidables

import com.badlogic.gdx.physics.box2d.Fixture
import com.rpg.game.systems.physics.collision.Collidable
import com.rpg.game.systems.physics.world.ObjectData

class GhostFireballProjectileCollidable(fixtureA: Fixture) extends Collidable {
  override def handleCollision(): Unit = {
    //set delete tag on fireball to be destroyed
    val userData = fixtureA.getBody.getUserData.asInstanceOf[ObjectData]
    userData.isFlaggedForDelete = true
  }
}
