package com.rpg.entity.item.equipment

import com.rpg.entity.Entity
import com.rpg.entity.data.EntityAccess


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
case class Armor(material: String,
                 price: Double,
                 weight: Double,
                 equipmentType: String,
                 durability: Double,
                 enchant: Enchant,
                 physicalDefense: Double,
                 magicalDefense: Double) extends BaseEquipmentEntity {
  override def entityAccess: EntityAccess = ???
  override def getEntityId: String = ???
  override def getEntityName: String = ???
  



}
