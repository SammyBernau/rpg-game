package com.rpg.entity.animate.entityconstructs

import com.rpg.entity.animate.BaseAnimateEntity
import com.rpg.entity.item.equipment.{Armor, BaseHumanoidEquipmentSetup, Enchant, Weapon}

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
                    sprintingSpeed: Float,
                    walkingSpeed: Float,
                    walkingState: Boolean,
                    x: Float,
                    y: Float,
                    equipment: BaseHumanoidEquipmentSetup) extends BaseAnimateEntity

