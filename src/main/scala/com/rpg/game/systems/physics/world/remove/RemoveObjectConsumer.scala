package com.rpg.game.systems.physics.world.remove

import com.badlogic.gdx.physics.box2d.{Body, World}
import com.badlogic.gdx.utils.Array
import com.rpg.game.structure.Consumer
import com.rpg.game.systems.physics.world.ObjectData
import com.rpg.game.systems.rendering.gameobjects.ObjectRenderingServiceHandler
import com.rpg.game.systems.rendering.{RenderEvent, RenderSystem}

import javax.inject.Inject

class RemoveObjectConsumer @Inject(val renderSystem: RenderSystem, world: World, removeObjectService: RemoveObjectCache, objectRenderingServiceHandler: ObjectRenderingServiceHandler) extends Consumer with RenderEvent {
  
  //must run on main thread since it updates physics world
  override def render(): Unit = {
    consume()
  }
  
  override def consume(): Unit = synchronized {
    val bodies = new Array[Body](world.getBodyCount)
    world.getBodies(bodies)

    bodies.forEach { body =>
      //to prevent some obscure c assertion that happened randomly once in a blue moon
      val list = body.getJointList
      while (list.size > 0) world.destroyJoint(list.get(0).joint)

      // actual remove
      val userData = body.getUserData.asInstanceOf[ObjectData]
      if (userData != null) {
        if (userData.isFlaggedForDelete) {
          objectRenderingServiceHandler.removeTexture(userData.getId)
          world.destroyBody(body)
          body.setUserData(null)
        }
      }
    }
  }

}
