package com.rpg.game.entity.animate

import com.rpg.game.entity.Entity
import com.rpg.game.entity.data.EntityAccess



/**
 * BaseAnimateEntity is the base entity class for which all livable entities inherit from (players, monsters, animals, etc.)
 */

//TODO check if I really need vars in this class as functional programming aims to keep things immutable
//Things like status effects may need to change attributes...
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

  override def getEntityTypeId: String = ???
  override def getEntityId: String = ???


  /*Checks if entity is dead based on stamina left*/
  def isEntityDead: Boolean = stamina == 0

  /*Basic getter & setter methods*/
  def getName: String = name
  def setName(newName: String): Unit
  def getLevel: Double = level
  def setLevel(newLevel: Double): Unit
  def getAge: Int = age
  def setAge(newAge: Int): Unit
  def getStamina: Double = stamina
  def setStamina(newStamina: Double): Unit
  def getStrength: Double = strength
  def setStrength(newStrength: Double): Unit
  def getAgility: Double = agility
  def setAgility(newAgility: Double): Unit
  def getIntellect: Double = intellect
  def setIntellect(newIntellect: Double): Unit
  def getSpirit: Double = spirit
  def setSpirit(newSpirit: Double): Unit
  def getCriticalStrikeChance: Double = criticalStrikeChance
  def setCriticalStrikeChance(newCriticalStrikeChance: Double): Unit
  def getHaste: Double = haste
  def setHaste(newHaste: Double): Unit
  def getPhysicalDefense: Double = physicalDefense
  def setPhysicalDefense(newPhysicalDefense: Double): Unit
  def getPhysicalDamage: Double = physicalDamage
  def setPhysicalDamage(newPhysicalDamage: Double): Unit
  def getMagicDefense: Double = magicDefense
  def setMagicDefense(newMagicDefense: Double): Unit
  def getMagicDamage: Double = magicDamage
  def setMagicDamage(newMagicDamage: Double): Unit

  /*Walking and sprinting speed will most likely be static for all entities*/
  def getSprintingSpeed: Double = sprintingSpeed
  def setSprintingSpeed(newSprintingSpeed: Double): Unit
  def getWalkingSpeed: Double = walkingSpeed
  def setWalkingSpeed(newWalkingSpeed: Double): Unit
  def getWalkingState: Boolean = walkingState
  /*
   * if value is set to true, entity is walking and not sprinting
   * if value is set to false, entity is always considered sprinting and not walking
   * */
  def setWalking(newWalkingState: Boolean): Unit


}
