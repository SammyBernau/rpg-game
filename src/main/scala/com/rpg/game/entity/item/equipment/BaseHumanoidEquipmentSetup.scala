package com.rpg.game.entity.item.equipment

/**
 *
 * @param headSlot: head equipment
 * @param neckSlot: necklace
 * @param chestSlot: chest piece
 * @param backSlot: cloak
 * @param handsSlot: gloves
 * @param legsSlot: pants
 * @param feetSlot: boots/shoes
 * @param weaponOneSlot: 1st weapon equipped
 * @param weaponTwoSlot: 2nd weapon equipped
 *
 * Note: for weaponOneSlot and weaponTwoSlot if a two-handed weapon is equipped in the first slot, a secondary weapon is not allowed in the 2nd slot
 */
class BaseHumanoidEquipmentSetup (var headSlot: Armor,
                                  var neckSlot: Armor,
                                  var chestSlot: Armor,
                                  var backSlot: Armor,
                                  var handsSlot: Armor,
                                  var legsSlot: Armor,
                                  var feetSlot: Armor,
                                  var weaponOneSlot: Weapon,
                                  var weaponTwoSlot: Weapon) {




  /*Checks the first weapon slot to see if its a 2 handed weapon and if so return true, indicating that weaponTwoSlot must be empty*/
  //TODO create the logic to ensure that weaponTwoSlot is empty when weaponOneSlot is set to two-handed
  def checkFor2Hand: Boolean = {
    if(weaponOneSlot.getEquipmentType.equalsIgnoreCase("Two-Handed")){
      return true
    }
    false
  }


}
