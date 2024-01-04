package com.rpg.game.entity

import com.rpg.game.data.DataAccess
import com.rpg.game.entity.data.EntityAccess

/**
 * An entity encompasses all game objects (player, items, enemies, etc.)
 * */
trait Entity {
  
  def entityAccess: EntityAccess
  
  /* 
   * Name for groups of entities
   * Example: Undead Skeleton = entity type
   * 
   * For more unique entities like special weapons EntityType will be the name of that item even if theres only one.
   * Example: Great Sword of Azoth = entity type
   * */
  //TODO if an item is meant to only ever have one occurrence in the game have some logic to check that only one exists?
  def getEntityTypeId: String
  /*
   * Unique identifier for each entity
   * keep a running tally of each entity as it is used and use that as entity Id (ie: numOfSkeletons = 1, numOfSkeletons = 2 etc.)
   * */
  def getEntityId: String

  
  
}



