package com.rpg.game.game.util.physics.removal

import com.badlogic.gdx.physics.box2d.Body
import com.rpg.game.entity.UserData
import com.rpg.game.game.config.GameConfig.GameWorld.WORLD
import com.badlogic.gdx.utils.Array

class ObjectRemover {
  /**
   * Safe way to remove body from the world. Remember that you cannot have any
   * references to this body after calling this
   *
   * @param body
   * that will be removed from the physic world
   */
  def removeBodySafely(): Unit = {
    val bodies = new Array[Body](WORLD.getBodyCount)
    WORLD.getBodies(bodies)

    bodies.forEach{ body =>
      val list = body.getJointList
      while (list.size > 0) WORLD.destroyJoint(list.get(0).joint)

      // actual remove
      val userData = body.getUserData.asInstanceOf[UserData]
      if(userData != null) {
        if (userData.isFlaggedForDelete) {
          WORLD.destroyBody(body)
          println("Body destroyed")
          body.setUserData(null)
        }
      }
    }
    //to prevent some obscure c assertion that happened randomly once in a blue moon
  }

}
