package com.rpg.game.entity

import com.rpg.game.data.DataAccess
import com.rpg.game.entity.data.EntityAccess

/**
 * An entity encompasses all game objects (player, items, enemies, etc.)
 * */
trait Entity {
  
  def entityAccess: EntityAccess
  
  /** 
   * Name for groups of entities
   * Example: Undead Skeleton = entity type
   * */
  def getEntityTypeId: String
  /**
   * Unique identifier for each entity
   * keep a running tally of each reoccurring entity and use that as entity Id (ie: skeleton 1, skeleton 2 etc.)
   * */
  def getEntityId: String

  
  
}



