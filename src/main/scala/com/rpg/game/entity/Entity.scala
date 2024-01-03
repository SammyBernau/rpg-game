package com.rpg.game.entity

import com.rpg.game.data.DataAccess
import com.rpg.game.entity.data.EntityAccess

trait Entity {
  
  def entityAccess: EntityAccess
  
  def getEntityId: String
  
  
}



