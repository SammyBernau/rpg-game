package com.rpg.game.entity.item.equipment

import com.rpg.game.entity.Entity


/**
 * Enchant trait encompasses all instances in which increased stats are applied to a animate entity or equipment.
 * When enchants are applied to equipment the bonus stat increase is applied to the humanoid using it.
 */
trait Enchant extends Entity {

  var staminaBuff: Double
  var strengthBuff: Double
  var agilityBuff: Double
  var intellectBuff: Double
  var spiritBuff: Double
  var criticalStrikeChanceBuff: Double
  var hasteBuff: Double
  var physicalDefenseBuff: Double
  var physicalDamageBuff: Double
  var magicDefenseBuff: Double
  var magicDamageBuff: Double
  var durabilityBuff: Double
  var sprintingSpeedBuff: Double
  var walkingSpeedBuff: Double
  
  /*Basic getters & setters */
  def getStaminaBuff: Double = staminaBuff
  def setStaminaBuff(newStaminaBuff: Double): Unit = staminaBuff = newStaminaBuff
  def getStrengthBuff: Double = strengthBuff
  def setStrengthBuff(newStrengthBuff: Double): Unit = strengthBuff = newStrengthBuff
  def getAgilityBuff: Double = agilityBuff
  def setAgilityBuff(newAgilityBuff: Double): Unit = agilityBuff = newAgilityBuff
  def getIntellectBuff: Double = intellectBuff
  def setIntellectBuff(newIntellectBuff: Double): Unit = intellectBuff = newIntellectBuff
  def getSpiritBuff: Double = spiritBuff
  def setSpiritBuff(newSpiritBuff: Double): Unit = spiritBuff = newSpiritBuff
  def getCriticalStrikeChanceBuff: Double = criticalStrikeChanceBuff
  def setCriticalStrikeChanceBuff(newCriticalStrikeChanceBuff: Double): Unit = criticalStrikeChanceBuff = newCriticalStrikeChanceBuff
  def getHasteBuff: Double = hasteBuff
  def setHasteBuff(newHasteBuff: Double): Unit = hasteBuff = newHasteBuff
  def getPhysicalDefenseBuff: Double = physicalDefenseBuff
  def setPhysicalDefenseBuff(newPhysicalDefenseBuff: Double): Unit = physicalDefenseBuff = newPhysicalDefenseBuff
  def getPhysicalDamageBuff: Double = physicalDamageBuff
  def setPhysicalDamageBuff(newPhysicalDamageBuff: Double): Unit = physicalDamageBuff = newPhysicalDamageBuff
  def getMagicDefenseBuff: Double = magicDefenseBuff
  def setMagicDefenseBuff(newMagicDefenseBuff: Double): Unit = magicDefenseBuff = newMagicDefenseBuff
  def getMagicDamageBuff: Double = magicDamageBuff
  def setMagicDamageBuff(newMagicDamageBuff: Double): Unit = magicDamageBuff = newMagicDamageBuff
  def getDurabilityBuff: Double = durabilityBuff
  def setDurabilityBuff(newDurabilityBuff: Double): Unit = durabilityBuff = newDurabilityBuff
  def getSprintingSpeedBuff: Double = sprintingSpeedBuff
  def setSprintingSpeedBuff(newSprintingSpeedBuff: Double): Unit = sprintingSpeedBuff = newSprintingSpeedBuff
  def getWalkingSpeedBuff: Double = walkingSpeedBuff
  def setWalkingSpeedBuff(newWalkingSpeed: Double): Unit = walkingSpeedBuff = newWalkingSpeed
  
  
  
  
}
