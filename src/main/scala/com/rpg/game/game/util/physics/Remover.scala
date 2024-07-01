package com.rpg.game.game.util.physics

import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.utils.Array
import com.rpg.game.entity.UserData
import com.rpg.game.game.config.CurrentWorld
import com.rpg.game.game.config.GameConfig.GameWorld.WORLD
import com.rpg.game.game.util.rendering.OrthogonalTiledMapRendererWithObjects

/**
 * Removes physics objects from world and calls OrthogonalTiledMapRendererWithObjects to remove their respective textures
 * @param renderer
 */
class Remover(renderer: OrthogonalTiledMapRendererWithObjects) {
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
      //to prevent some obscure c assertion that happened randomly once in a blue moon
      val list = body.getJointList
      while (list.size > 0) WORLD.destroyJoint(list.get(0).joint)

      // actual remove
      val userData = body.getUserData.asInstanceOf[UserData]
      if(userData != null) {
        if (userData.isFlaggedForDelete) {
          renderer.removeTexture(userData.getId)
          WORLD.destroyBody(body)
          body.setUserData(null)
        }
      }
    }
  }
  

}