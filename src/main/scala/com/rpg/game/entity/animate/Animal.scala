package com.rpg.game.entity.animate

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
                  var walkingState: Boolean) extends BaseAnimateEntity {
  // Animals-specific fields and methods
  
  
  //test method
  def makeAnimalSound(): Unit = {
    print("moo")
  }
}
