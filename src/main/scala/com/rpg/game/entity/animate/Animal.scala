package com.rpg.game.entity.animate

import com.rpg.game.entity.item.equipment.BaseEquipmentEntity


/**
 * The parameters name, level, age, stamina, strength, agility, intellect, spirit, criticalStrikeChance, haste, physicalDefense,
 * physicalDamage,magicDefense,magicDamage,sprintingSpeed,walkingSpeed,walkingState,x,y are all defined in [[BaseAnimateEntity]]
 */
//TODO not sure if animals need any more params then base ones given in BaseEquipmentEntity
class Animal(var name: String,
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
                  var y: Float) extends BaseAnimateEntity {
  // Animals-specific fields and methods
  
  
  //test method
  def makeAnimalSound(): Unit = {
    println("moo")
  }
}
