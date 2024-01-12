package com.rpg.game.entity.animate

import com.rpg.game.entity.Entity
import com.rpg.game.entity.data.EntityAccess



/**
 * BaseAnimateEntity is the base entity class for which all livable entities inherit from (players, monsters, animals, etc.)
 */


trait BaseAnimateEntity extends Entity {
  //TODO think about what might change a lot and var might be more beneficial
  /*name given*/
  val name: String
  /*experience level*/
  val level: Double
  /*natural age*/
  val age: Int
  /*health pool*/
  val stamina: Double
  /*strength level factored into physicalDamage and physicalDefense*/
  val strength: Double
  /*ability to dodge any attack. Also factored into hit chance for physical attacks*/
  val agility: Double
  /*mana pool, magic level factored into magicDamage and magicDamage and ability to know certain spells*/
  val intellect: Double
  /*mana regeneration rate*/
  val spirit: Double
  /*chance to land a critical hit against an opponent (magic or physical)*/
  val criticalStrikeChance: Double
  /*rate at which magic spells are caste attacks*/
  val haste: Double
  /*defense level factored into damage taken when struck with physical attacks (fists, swords, arrows)*/
  val physicalDefense: Double
  /*damage caused by physical attacks taking strength into account*/
  val physicalDamage: Double
  /*defense level factored into damage taken when struck with magic (spells)*/
  val magicDefense: Double
  /*damage caused by magic attacks taking intellect into account*/
  val magicDamage: Double
  /*sprinting rate of entity*/
  val sprintingSpeed: Double
  /*walking rate of entity*/
  val walkingSpeed: Double
  /*state for entity for whether its walking or not (true = walking, false = sprinting)*/
  val walkingState: Boolean
  /*x coordinate for libGDX formatting*/
  val x: Float
  /*y coordinate for libGDX formatting*/
  val y: Float
  
  override def entityAccess: EntityAccess = ???

  override def getEntityName: String = ???
  override def getEntityId: String = ???


  /*Checks if entity is dead based on stamina left*/
  def isEntityDead: Boolean = stamina == 0


}
