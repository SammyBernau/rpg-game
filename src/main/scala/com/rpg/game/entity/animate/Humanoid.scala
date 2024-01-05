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



  //Notes for later
  /*equipment.getHeadSlot match {
    case Some(armor) => // do something with armor
    case None => // handle the case where there is no armor in the head slot
  }*/

  /*Basic setters & getters */
  def getHeadSlot: Option[Armor] = equipment.headSlot
  def setHeadSlot(newArmor: Option[Armor]): Unit = equipment.headSlot = newArmor
  def getNeckSlot: Option[Armor] = equipment.neckSlot
  def setNeckSlot(newNeck: Option[Armor]): Unit = equipment.neckSlot = newNeck
  def getChestSlot: Option[Armor] = equipment.chestSlot
  def setChestSlot(newChest: Option[Armor]): Unit = equipment.chestSlot = newChest
  def getBackSlot: Option[Armor] = equipment.backSlot
  def setBackSlot(newBack: Option[Armor]): Unit = equipment.backSlot = newBack
  def getHandsSlot: Option[Armor] = equipment.handsSlot
  def setHandsSlot(newHands: Option[Armor]): Unit = equipment.handsSlot = newHands
  def getLegsSlot: Option[Armor] = equipment.legsSlot
  def setLegsSlot(newLegs: Option[Armor]): Unit = equipment.legsSlot = newLegs
  def getFeetSlot: Option[Armor] = equipment.feetSlot
  def setFeetSlot(newFeet: Option[Armor]): Unit = equipment.feetSlot = newFeet
  def getWeaponOneSlot: Option[Weapon] = equipment.weaponOneSlot
  def setWeaponOneSlot(newWeaponOne: Option[Weapon]): Unit = equipment.weaponOneSlot = newWeaponOne
  def getWeaponTwoSlot: Option[Weapon] = equipment.weaponTwoSlot
  def setWeaponTwoSlot(newWeaponTwo: Option[Weapon]): Unit = equipment.weaponTwoSlot = newWeaponTwo

}

