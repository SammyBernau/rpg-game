package com.rpg.game.game.util.physics.collision.collidables

import com.badlogic.gdx.physics.box2d.Fixture
import com.rpg.game.entity.UserData
import com.rpg.game.game.config.GameConfig.GameWorld.WORLD
import com.rpg.game.game.util.physics.collision.Collidable

class GhostFireballProjectileCollidable(fixtureA: Fixture) extends Collidable {
  override def handleCollision(): Unit = {
    //destroy fireball on contact
    val userData = fixtureA.getBody.getUserData.asInstanceOf[UserData]
    userData.isFlaggedForDelete = true
    println("Fireball collided with something")
  }
}
