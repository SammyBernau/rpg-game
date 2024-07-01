package com.rpg.game.entity

/**
 * Custom data holder for box2D body objects Userdata field
 */
class UserData(objName: String, var isFlaggedForDelete: Boolean, id: String) {

  def getObjName: String = objName
  def getId: String = id

}
