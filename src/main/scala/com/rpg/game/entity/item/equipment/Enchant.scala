package com.rpg.game.entity.item.equipment

import com.rpg.game.entity.Entity


/**
 * Enchant trait encompasses all instances in which increased stats are applied to a animate entity or equipment.
 * When enchants are applied to equipment the bonus stat increase is applied to the humanoid using it.
 */
trait Enchant extends Entity {

  var staminaBuff: Double
  var strengthBuff: Double
  var agilityBuff: Double
  var intellectBuff: Double
  var spiritBuff: Double
  var criticalStrikeChanceBuff: Double
  var hasteBuff: Double
  var physicalDefenseBuff: Double
  var physicalDamageBuff: Double
  var magicDefenseBuff: Double
  var magicDamageBuff: Double
  var durabilityBuff: Double
  var sprintingSpeedBuff: Double
  var walkingSpeedBuff: Double
}
