package com.rpg.game.entity.animate

import com.rpg.game.entity.item.equipment.{Armor, BaseHumanoidEquipmentSetup, Enchant, Weapon}

/**
 * The parameters name, level, age, stamina, strength, agility, intellect, spirit, criticalStrikeChance, haste, physicalDefense,
 * physicalDamage,magicDefense,magicDamage,sprintingSpeed,walkingSpeed,walkingState,x,y are all defined in [[BaseAnimateEntity]]
 * 
 * @param equipment: equipment that humanoid entity is wearing
 */
class Humanoid(var name: String,
               var level: Double,
               var age: Int,
               var stamina: Double,
               var strength: Double,
               var agility: Double,
               var intellect: Double,
               var spirit: Double,
               var criticalStrikeChance: Double,
               var haste: Double,
               var physicalDefense: Double,
               var physicalDamage: Double,
               var magicDefense: Double,
               var magicDamage: Double,
               var sprintingSpeed: Double,
               var walkingSpeed: Double,
               var walkingState: Boolean,
               var x: Float,
               var y: Float,
               var equipment: BaseHumanoidEquipmentSetup) extends BaseAnimateEntity {

  
  //TODO have some logic 
  /*Basic setters & getters */
  def getHeadSlot: Armor = equipment.headSlot
  def setHeadSlot(newArmor: Armor): Unit = equipment.headSlot = newArmor
  def getNeckSlot: Armor = equipment.neckSlot
  def setNeckSlot(newNeck: Armor): Unit = equipment.neckSlot = newNeck
  def getChestSlot: Armor = equipment.chestSlot
  def setChestSlot(newChest: Armor): Unit = equipment.chestSlot = newChest
  def getBackSlot: Armor = equipment.backSlot
  def setBackSlot(newBack: Armor): Unit = equipment.backSlot = newBack
  def getHandsSlot: Armor = equipment.handsSlot
  def setHandsSlot(newHands: Armor): Unit = equipment.handsSlot = newHands
  def getLegsSlot: Armor = equipment.legsSlot
  def setLegsSlot(newLegs: Armor): Unit = equipment.legsSlot = newLegs
  def getFeetSlot: Armor = equipment.feetSlot
  def setFeetSlot(newFeet: Armor): Unit = equipment.feetSlot = newFeet
  def getWeaponOneSlot: Weapon = equipment.weaponOneSlot
  def setWeaponOneSlot(newWeaponOne: Weapon): Unit = equipment.weaponOneSlot = newWeaponOne
  def getWeaponTwoSlot: Weapon = equipment.weaponTwoSlot
  def setWeaponTwoSlot(newWeaponTwo: Weapon): Unit = equipment.weaponTwoSlot = newWeaponTwo

}

