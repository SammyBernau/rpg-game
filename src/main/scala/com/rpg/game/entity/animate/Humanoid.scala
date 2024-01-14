package com.rpg.game.entity.animate

import com.rpg.game.entity.item.equipment.{Armor, BaseHumanoidEquipmentSetup, Enchant, Weapon}
import com.rpg.game.entity.animate.BaseAnimateEntity

/**
 * The parameters name, level, age, stamina, strength, agility, intellect, spirit, criticalStrikeChance, haste, physicalDefense,
 * physicalDamage,magicDefense,magicDamage,sprintingSpeed,walkingSpeed,walkingState,x,y are all defined in [[BaseAnimateEntity]]
 *
 * @param equipment: equipment that humanoid entity is wearing
 */
case class Humanoid(name: String,
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
                    sprintingSpeed: Double,
                    walkingSpeed: Double,
                    walkingState: Boolean,
                    x: Float,
                    y: Float,
                    equipment: BaseHumanoidEquipmentSetup) extends BaseAnimateEntity

