package com.rpg.game.systems.physics_system

import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.utils.Array
import com.rpg.entity.ObjectUserData
import com.rpg.game.config.CurrentSettings
import com.rpg.game.systems.physics_system.World.WORLD
import com.rpg.game.systems.rendering_system.RendererWithObjects

/**
 * Removes physics objects from world and calls OrthogonalTiledMapRendererWithObjects to remove their respective textures
 * @param renderer
 */
class Remover(renderer: RendererWithObjects) {
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
      val userData = body.getUserData.asInstanceOf[ObjectUserData]
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
