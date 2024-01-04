package com.rpg.game.entity.item.equipment

import com.rpg.game.entity.Entity
import com.rpg.game.entity.data.EntityAccess


/**
 * Armor encompasses all items that can be worn by humanoid entities on the body (cloaks, helmets, chest plates etc.)
 * @param material: defined in [[BaseEquipmentEntity]]
 * @param price: defined in [[BaseEquipmentEntity]]
 * @param weight: defined in [[BaseEquipmentEntity]]
 * @param equipmentType: defined in [[BaseEquipmentEntity]]
 *
 * @param physicalDefense: protection level against physical attacks
 * @param magicalDefense: protection level against magical attacks
 */
//TODO convert material and covering to enums?
class Armor(var material: String,
            var price: Double,
            var weight: Double,
            var equipmentType: String,
            var durability: Double,
            var enchant: Enchant,
            var physicalDefense: Double,
            var magicalDefense: Double) extends BaseEquipmentEntity {
  override def entityAccess: EntityAccess = ???
  override def getEntityId: String = ???
  override def getEntityTypeId: String = ???

  /*Basic getters & setters*/
  def getPhysicalDefense: Double = physicalDefense
  def setPhysicalDefense(newPhysicalDefense: Double): Unit = physicalDefense = newPhysicalDefense
  def getMagicalDefense: Double = magicalDefense
  def setMagicalDefense(newMagicalDefense: Double): Unit = magicalDefense = newMagicalDefense



}
