package com.rpg.game.systems.physics.world.remove

import com.badlogic.gdx.physics.box2d.Body
import com.rpg.game.structure.Producer

import javax.inject.Inject

class RemoveObjectProducer @Inject(removeObjectService: RemoveObjectCache) extends Producer[Body] {

  //can technically run on another thread besides main
  
  override def produce(body: Body): Unit = removeObjectService.add(body)
  
}
