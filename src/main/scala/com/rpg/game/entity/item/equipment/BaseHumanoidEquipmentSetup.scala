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
class BaseHumanoidEquipmentSetup (var headSlot: Option[Armor],
                                  var neckSlot:Option[Armor],
                                  var chestSlot: Option[Armor],
                                  var backSlot: Option[Armor],
                                  var handsSlot: Option[Armor],
                                  var legsSlot: Option[Armor],
                                  var feetSlot: Option[Armor],
                                  var weaponOneSlot: Option[Weapon],
                                  var weaponTwoSlot: Option[Weapon]) {




  /*Checks the first weapon slot to see if its a 2 handed weapon and if so return true, indicating that weaponTwoSlot must be empty*/
  //create an enum for "Two-handed" "One-handed" etc. 
  def checkFor2Hand: Boolean = {
    weaponOneSlot match {
      case Some(weapon) if weapon.getEquipmentType.equalsIgnoreCase("Two-handed") => 
        weaponTwoSlot = None
        true
      case _ => false
    }
  }


}
