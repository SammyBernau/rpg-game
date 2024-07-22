package com.rpg.game.systems.physics

import com.badlogic.gdx.physics.box2d.{Body, World}
import com.badlogic.gdx.utils.Array
import com.rpg.entity.ObjectUserData
import com.rpg.game.config.CurrentMasterConfig
import com.rpg.game.systems.Listener
import com.rpg.game.systems.physics.world.add.PhysicsObjectConsumer
import com.rpg.game.systems.rendering.{RenderListener, RenderSystem}
import com.rpg.game.systems.rendering.services.gameobjects.{ObjectRenderingService, ObjectRenderingServiceHandler}
import com.rpg.game.systems.tick.{TickListener, TickSystem}

import javax.inject.Inject

/**
 * Removes physics objects from world and calls OrthogonalTiledMapRendererWithObjects to remove their respective textures
 */
class Remover @Inject(world: World,renderSystem: RenderSystem,objectRenderingServiceHandler: ObjectRenderingServiceHandler) extends RenderListener {

  renderSystem.addListener(this)


  override def renderListener(): Unit = {
    removeBodySafely()
  }

  /**
   * Safe way to remove body from the world. Remember that you cannot have any
   * references to this body after calling this
   * @param body -> that will be removed from the physic world
   *
   * Made with the help of a StackOverflow user which I modified
   */
  private def removeBodySafely(): Unit = {
    val bodies = new Array[Body](world.getBodyCount)
    world.getBodies(bodies)

    bodies.forEach{ body =>
      //to prevent some obscure c assertion that happened randomly once in a blue moon
      val list = body.getJointList
      while (list.size > 0) world.destroyJoint(list.get(0).joint)

      // actual remove
      val userData = body.getUserData.asInstanceOf[ObjectUserData]
      if(userData != null) {
        if (userData.isFlaggedForDelete) {
          objectRenderingServiceHandler.removeTexture(userData.getId)
          world.destroyBody(body) //TODO -> create another cache that will hold bodies waiting to be destroyed
          body.setUserData(null)
        }
      }
    }
  }
  

}
