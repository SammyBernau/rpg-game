package com.rpg.game.entity.animate

import com.rpg.game.entity.Entity
import com.rpg.game.entity.data.EntityAccess



/**
 * BaseAnimateEntity is the base entity class for which all livable entities inherit from (players, monsters, animals, etc.)
 */

//TODO check if I really need vars in this class as functional programming aims to keep things immutable
//Things like status effects may need to change attributes...
trait BaseAnimateEntity extends Entity {

  /*name given*/
  var name: String
  /*experience level*/
  var level: Double
  /*natural age*/
  var age: Int
  /*health pool*/
  var stamina: Double
  /*strength level factored into physicalDamage and physicalDefense*/
  var strength: Double
  /*ability to dodge any attack. Also factored into hit chance for physical attacks*/
  var agility: Double
  /*mana pool, magic level factored into magicDamage and magicDamage and ability to know certain spells*/
  var intellect: Double
  /*mana regeneration rate*/
  var spirit: Double
  /*chance to land a critical hit against an opponent (magic or physical)*/
  var criticalStrikeChance: Double
  /*rate at which magic spells are caste attacks*/
  var haste: Double
  /*defense level factored into damage taken when struck with physical attacks (fists, swords, arrows)*/
  var physicalDefense: Double
  /*damage caused by physical attacks taking strength into account*/
  var physicalDamage: Double
  /*defense level factored into damage taken when struck with magic (spells)*/
  var magicDefense: Double
  /*damage caused by magic attacks taking intellect into account*/
  var magicDamage: Double
  /*sprinting rate of entity*/
  var sprintingSpeed: Double
  /*walking rate of entity*/
  var walkingSpeed: Double
  /*state for entity for whether its walking or not (true = walking, false = sprinting)*/
  var walkingState: Boolean
  /*x coordinate for libGDX formatting*/
  var x: Float
  /*y coordinate for libGDX formatting*/
  var y: Float
  
  override def entityAccess: EntityAccess = ???

  override def getEntityTypeId: String = ???
  override def getEntityId: String = ???


  /*Checks if entity is dead based on stamina left*/
  def isEntityDead: Boolean = stamina == 0

  /*Basic getter & setter methods*/
  def getName: String = name
  def setName(newName: String): Unit = name = newName
  def getLevel: Double = level
  def setLevel(newLevel: Double): Unit = level = newLevel
  def getAge: Int = age
  def setAge(newAge: Int): Unit = age = newAge
  def getStamina: Double = stamina
  def setStamina(newStamina: Double): Unit = stamina = newStamina
  def getStrength: Double = strength
  def setStrength(newStrength: Double): Unit = strength = newStrength
  def getAgility: Double = agility
  def setAgility(newAgility: Double): Unit = agility = newAgility
  def getIntellect: Double = intellect
  def setIntellect(newIntellect: Double): Unit = intellect = newIntellect
  def getSpirit: Double = spirit
  def setSpirit(newSpirit: Double): Unit = spirit = newSpirit
  def getCriticalStrikeChance: Double = criticalStrikeChance
  def setCriticalStrikeChance(newCriticalStrikeChance: Double): Unit = criticalStrikeChance = newCriticalStrikeChance
  def getHaste: Double = haste
  def setHaste(newHaste: Double): Unit = haste = newHaste
  def getPhysicalDefense: Double = physicalDefense
  def setPhysicalDefense(newPhysicalDefense: Double): Unit = physicalDefense = newPhysicalDefense
  def getPhysicalDamage: Double = physicalDamage
  def setPhysicalDamage(newPhysicalDamage: Double): Unit = physicalDamage = newPhysicalDamage
  def getMagicDefense: Double = magicDefense
  def setMagicDefense(newMagicDefense: Double): Unit = magicDefense = newMagicDefense
  def getMagicDamage: Double = magicDamage
  def setMagicDamage(newMagicDamage: Double): Unit = magicDamage = newMagicDamage

  /*Walking and sprinting speed will most likely be static for all entities*/
  def getSprintingSpeed: Double = sprintingSpeed
  def setSprintingSpeed(newSprintingSpeed: Double): Unit = sprintingSpeed = newSprintingSpeed
  def getWalkingSpeed: Double = walkingSpeed
  def setWalkingSpeed(newWalkingSpeed: Double): Unit = walkingSpeed = newWalkingSpeed
  def getWalkingState: Boolean = walkingState
  /*
   * if value is set to true, entity is walking and not sprinting
   * if value is set to false, entity is always considered sprinting and not walking
   * */
  def setWalking(newWalkingState: Boolean): Unit = walkingState = newWalkingState


}
