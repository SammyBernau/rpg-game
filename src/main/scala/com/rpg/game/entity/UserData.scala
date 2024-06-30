package com.rpg.game.entity

/**
 * Custom data holder for box2D body objects Userdata field
 */
class UserData(var objName: String, var isFlaggedForDelete: Boolean, id: String) {
  
  def getId: String = id

}
