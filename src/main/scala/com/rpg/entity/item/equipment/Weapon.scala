package com.rpg.entity.item.equipment

import com.rpg.entity.Entity
import com.rpg.entity.data.EntityAccess


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
  override def getEntityName: String = ???
}
