package com.rpg.entity

import com.rpg.persistence.DataAccess
import com.rpg.entity.data.EntityAccess

/**
 * An entity encompasses all game objects (player, items, enemies, etc.)
 * */
trait Entity {
  //maybe add a size field to entity? A width/height as well?
  
  def entityAccess: EntityAccess
  
  /**
   * For non-player entities:
        EntityName is the name for groups of entities
          - Example: Undead Skeleton = entity type
        For more unique entities where we want only one occurrence EntityName will be the same as EntityId
          - have a check 'onlyOne = true or false' to annotate this in JSON
          Example:
            - EntityName = "Great Sword of Azoth"
            - EntityId = "Great Sword of Azoth"
   * For player entities:
      EntityName is always set to 'player'
   **/
  //if an item is meant to only ever have one occurrence in the game have some logic to check that only one exists
  def getEntityName: String
  /**
   * For non-player entities:
      EntityId is the unique identifier for each entity regardless if it belongs to a group
        - For entity groups EntityId may not have be stored in JSON and only relevant during runtime. No need to store 5 instances of Undead Skeleton, all we need is the runningTally
      Note:
        For entities that are apart of a group such as 'Undead Skeleton' an individual Undead Skeleton's EntityId is 'EntityName + runningTally'
          - runningTally = the running count of how many times that entity has been used starting at 0
   * For player entities: 
      EntityId = player's entered username
        - for local: 
          - player data and game save data will be located by player's username
        - for online: 
          - player save data will be located by player's username
          - if joining another player's session only player data will be transferred and the hosting player's game data will be used similar to how Terraria's world saves and player data are separated
   **/
  def getEntityId: String
  
  //maybe every entity has its up tick update method
  //look into game tick designs

  
  
}



