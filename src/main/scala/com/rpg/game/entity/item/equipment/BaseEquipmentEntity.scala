package com.rpg.game.entity.item.equipment

import com.rpg.game.entity.Entity
import com.rpg.game.entity.data.EntityAccess

/**
 * BaseEquipmentEntity trait is the base state of all equipment that might be used or worn by humanoid creatures.
 * */

trait BaseEquipmentEntity extends Entity{

  /*what the equipment is made of (leather, iron, steel, wood, etc.)*/
  var material: String
  /*price buyer pays to obtain item from player or humanoid entity*/
  var price: Double
  /*physical weight of equipment*/
  var weight: Double
  /*type of equipment according to its use (longsword = Two-handed weapon, dagger = one-handed weapon, helmet = head piece, cloak = back piece, etc.)*/
  var equipmentType: String
  /*durability of equipment that degrades during use until it is considered broken at 1/x (x being the max durability of specific equipment)*/
  var durability: Double
  /*Enchant applied to equipment that transfers to humanoid using it*/
  var enchant: Enchant


  override def entityAccess: EntityAccess = ???
  override def getEntityTypeId: String = ???
  override def getEntityId: String = ???
  
  /*Basic setters & getters*/

  def getMaterial: String = material
  def setMaterial(newMaterial: String): Unit = material = newMaterial
  def getPrice: Double = price
  def setPrice(newPrice: Double): Unit = price = newPrice
  def getWeight: Double = weight
  def setWeight(newWeight: Double): Unit = weight = newWeight
  def getEquipmentType: String = equipmentType
  def setEquipmentType(newEquipmentType: String): Unit = equipmentType = newEquipmentType
  def getDurability: Double = durability
  def setDurability(newDurability: Double): Unit = durability = newDurability
  def getEnchant: Enchant = enchant

}
