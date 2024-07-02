package com.rpg.entity.item.equipment

import com.rpg.entity.Entity
import com.rpg.entity.data.EntityAccess

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
  override def getEntityName: String = ???
  override def getEntityId: String = ???

}
