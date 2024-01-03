package com.rpg.game.entity.animate

import com.rpg.game.entity.Entity
import com.rpg.game.entity.data.EntityAccess



/**
 * BaseAnimateEntity is the base entity class for which all livable entities inherit from (players, monsters, animals, etc.)
 *
 *
 *
 * name: name of entity
 * age: natural age of entity
 * stamina: health pool
 * strength: strength level factored into physical damage and physicalDefense
 * agility: ability to dodge any attack. Also factored into hit chance for physical attacks
 * intellect: mana pool, magic level factored into magicDamage and magicDamage and ability to know certain spells
 * spirit: mana regeneration speed
 * criticalStrikeChance: chance to land a critical hit against an opponent (magic or physical)
 * haste: speed at which magic spells are caste attacks
 * physicalDefense: defense level factored into damage taken when struck with physical attacks (fists, swords, arrows)
 * physicalDamage: damage caused by physical attacks taking strength into account
 * magicDefense: defense level factored into damage taken when struck with magic (spells)
 * magicDamage: damage caused by magic attacks taking intellect into account
 * sprintingSpeed: sprinting speed of entity
 * walkingSpeed: walking speed of entity
 * walkingState: state for entity for whether its walking or not (true = walking, false = sprinting)
 * */

//TODO check if I really need vars in this class as functional programming aims to keep things immutable
//Things like status effects may need to change attributes...
trait BaseAnimateEntity extends Entity {

  var name: String
  var level: Double
  var age: Int
  var stamina: Double
  var strength: Double
  var agility: Double
  var intellect: Double
  var spirit: Double
  var criticalStrikeChance: Double
  var haste: Double
  var physicalDefense: Double
  var physicalDamage: Double
  var magicDefense: Double
  var magicDamage: Double
  var sprintingSpeed: Double
  var walkingSpeed: Double
  var walkingState: Boolean
  
  override def entityAccess: EntityAccess = ???

  override def getEntityTypeId: String = ???
  override def getEntityId: String = ???


  /**
   * Checks if entity is dead based of stamina left*/
  def isEntityDead: Boolean = stamina == 0

  /**Basic getter & setter methods*/
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

  /**
   * Walking and sprinting speed will most likely be static for all entities
   * */
  def getSprintingSpeed: Double = sprintingSpeed
  def setSprintingSpeed(newSprintingSpeed: Double): Unit = sprintingSpeed = newSprintingSpeed
  def getWalkingSpeed: Double = walkingSpeed
  def setWalkingSpeed(newWalkingSpeed: Double): Unit = walkingSpeed = newWalkingSpeed
  def getWalkingState: Boolean = walkingState
  /**
   * if value is set to true, entity is walking and not sprinting
   * if value is set to false, entity is always considered sprinting and not walking
   * */
  def setWalking(newWalkingState: Boolean): Unit = walkingState = newWalkingState


}
