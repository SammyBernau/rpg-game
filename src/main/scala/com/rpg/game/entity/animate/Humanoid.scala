package com.rpg.game.entity.animate

import com.rpg.game.entity.item.equipment.{Armor, BaseHumanoidEquipmentSetup, Enchant, Weapon}
import com.rpg.game.entity.animate.BaseAnimateEntity

/**
 * The parameters name, level, age, stamina, strength, agility, intellect, spirit, criticalStrikeChance, haste, physicalDefense,
 * physicalDamage,magicDefense,magicDamage,sprintingSpeed,walkingSpeed,walkingState,x,y are all defined in [[BaseAnimateEntity]]
 *
 * @param equipment: equipment that humanoid entity is wearing
 */
case class Humanoid(name: String,
                    level: Double,
                    age: Int,
                    stamina: Double,
                    strength: Double,
                    agility: Double,
                    intellect: Double,
                    spirit: Double,
                    criticalStrikeChance: Double,
                    haste: Double,
                    physicalDefense: Double,
                    physicalDamage: Double,
                    magicDefense: Double,
                    magicDamage: Double,
                    sprintingSpeed: Double,
                    walkingSpeed: Double,
                    walkingState: Boolean,
                    x: Float,
                    y: Float,
                    equipment: BaseHumanoidEquipmentSetup) extends BaseAnimateEntity {



  //Notes for later
  /*equipment.getHeadSlot match {
    case Some(armor) => // do something with armor
    case None => // handle the case where there is no armor in the head slot
  }*/

  /*Basic setters & getters */
  def getHeadSlot: Option[Armor] = equipment.headSlot
  def setHeadSlot(newArmor: Option[Armor]): Unit
  def getNeckSlot: Option[Armor] = equipment.neckSlot
  def setNeckSlot(newNeck: Option[Armor]): Unit
  def getChestSlot: Option[Armor] = equipment.chestSlot
  def setChestSlot(newChest: Option[Armor]): Unit
  def getBackSlot: Option[Armor] = equipment.backSlot
  def setBackSlot(newBack: Option[Armor]): Unit
  def getHandsSlot: Option[Armor] = equipment.handsSlot
  def setHandsSlot(newHands: Option[Armor]): Unit
  def getLegsSlot: Option[Armor] = equipment.legsSlot
  def setLegsSlot(newLegs: Option[Armor]): Unit
  def getFeetSlot: Option[Armor] = equipment.feetSlot
  def setFeetSlot(newFeet: Option[Armor]): Unit
  def getWeaponOneSlot: Option[Weapon] = equipment.weaponOneSlot
  def setWeaponOneSlot(newWeaponOne: Option[Weapon]): Unit
  def getWeaponTwoSlot: Option[Weapon] = equipment.weaponTwoSlot
  def setWeaponTwoSlot(newWeaponTwo: Option[Weapon]): Unit

}

