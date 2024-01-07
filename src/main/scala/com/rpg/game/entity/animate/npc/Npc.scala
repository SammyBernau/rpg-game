package com.rpg.game.entity.animate.npc

import com.rpg.game.entity.Entity
import com.rpg.game.entity.data.EntityAccess

//TODO check and see if an NPC file is actually needed. This might be unnecessary since a file for Animal and Humanoid is already created.
//Think of any additional methods that an NPC might have that a player humanoid might not (basic speech? trade windows? etc.)
class Npc extends Entity {
  override def entityAccess: EntityAccess = ???
  override def getEntityName: String = ???
  override def getEntityId: String = ???

}

