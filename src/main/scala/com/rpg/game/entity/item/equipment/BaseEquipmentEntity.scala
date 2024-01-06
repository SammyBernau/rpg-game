package com.rpg.game.entity.item.equipment

import com.rpg.game.entity.Entity
import com.rpg.game.entity.data.EntityAccess

/**
 * BaseEquipmentEntity trait is the base state of all equipment that might be used or worn by humanoid creatures.
 * */

trait BaseEquipmentEntity extends Entity{

  /*what the equipment is made of (leather, iron, steel, wood, etc.)*/
  val material: String
  /*price buyer pays to obtain item from player or humanoid entity*/
  val price: Double
  /*physical weight of equipment*/
  val weight: Double
  /*type of equipment according to its use (longsword = Two-handed weapon, dagger = one-handed weapon, helmet = head piece, cloak = back piece, etc.)*/
  val equipmentType: String
  /*durability of equipment that degrades during use until it is considered broken at 1/x (x being the max durability of specific equipment)*/
  val durability: Double
  /*Enchant applied to equipment that transfers to humanoid using it*/
  val enchant: Enchant


  override def entityAccess: EntityAccess = ???
  override def getEntityTypeId: String = ???
  override def getEntityId: String = ???
  
  /*Basic setters & getters*/

  def getMaterial: String = material
  def setMaterial(newMaterial: String): Unit
  def getPrice: Double = price
  def setPrice(newPrice: Double): Unit
  def getWeight: Double = weight
  def setWeight(newWeight: Double): Unit
  def getEquipmentType: String = equipmentType
  def setEquipmentType(newEquipmentType: String): Unit
  def getDurability: Double = durability
  def setDurability(newDurability: Double): Unit
  def getEnchant: Enchant = enchant

}
