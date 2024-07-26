package com.rpg.entity.animate.entityconstructs

import com.rpg.entity.animate.BaseAnimateEntity


/**
 * The parameters name, level, age, stamina, strength, agility, intellect, spirit, criticalStrikeChance, haste, physicalDefense,
 * physicalDamage,magicDefense,magicDamage,sprintingSpeed,walkingSpeed,walkingState,x,y are all defined in [[BaseAnimateEntity]]
 */
//not sure if animals need any more params then base ones given in BaseEquipmentEntity
case class Animal(name: String,
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
                  sprintingSpeed: Float,
                  walkingSpeed: Float,
                  walkingState: Boolean,
                  x: Float,
                  y: Float) extends BaseAnimateEntity {
  // Animals-specific fields and methods
  
  
  //test method
  def makeAnimalSound(): Unit = {
    println("moo")
  }
}
