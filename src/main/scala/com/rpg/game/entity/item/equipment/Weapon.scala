package com.rpg.game.entity.item.equipment

import com.rpg.game.entity.Entity
import com.rpg.game.entity.data.EntityAccess


/**
 * Weapon class encompasses all items that can be used as a weapon by humanoid entities (swords, axes, staffs, bows, wands, etc.)
 *
 * @param material: defined in [[BaseEquipmentEntity]]
 * @param price: defined in [[BaseEquipmentEntity]]
 * @param weight: defined in [[BaseEquipmentEntity]]
 * @param equipmentType: defined in [[BaseEquipmentEntity]]
 * 
 * 
 * @param physicalDamage: damage caused by physical attack
 * @param magicalDamage: damage caused by magical attack
 */

//TODO convert material and weaponType to enums?
case class Weapon(material: String,
                  price: Double,
                  weight: Double,
                  equipmentType: String,
                  durability: Double,
                  enchant: Enchant,
                  physicalDamage: Double,
                  magicalDamage: Double) extends BaseEquipmentEntity {


  override def entityAccess: EntityAccess = ???
  override def getEntityId: String = ???
  override def getEntityTypeId: String = ???
  
  
  /*Basic setters & getters */
  def getPhysicalDamage: Double = physicalDamage
  def setPhysicalDamage(newPhysicalDamage: Double): Unit
  def getMagicalDamage: Double = magicalDamage
  def setMagicalDamage(newMagicalDamage: Double): Unit
  

}
