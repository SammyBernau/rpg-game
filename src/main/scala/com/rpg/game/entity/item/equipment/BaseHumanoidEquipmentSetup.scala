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
case class BaseHumanoidEquipmentSetup (headSlot: Option[Armor],
                                  neckSlot:Option[Armor],
                                  chestSlot: Option[Armor],
                                  backSlot: Option[Armor],
                                  handsSlot: Option[Armor],
                                  legsSlot: Option[Armor],
                                  feetSlot: Option[Armor],
                                  weaponOneSlot: Option[Weapon],
                                  weaponTwoSlot: Option[Weapon]) {




  /*Checks the first weapon slot to see if its a 2 handed weapon and if so return true, indicating that weaponTwoSlot must be empty*/
  //create an enum for "Two-handed" "One-handed" etc. 
//  def checkFor2Hand: Boolean = {
//    weaponOneSlot match {
//      case Some(weapon) if weapon.getEquipmentType.equalsIgnoreCase("Two-handed") => 
//        weaponTwoSlot = None
//        true
//      case _ => false
//    }
//  }


}
