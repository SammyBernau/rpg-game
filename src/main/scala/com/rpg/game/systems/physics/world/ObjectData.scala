package com.rpg.game.systems.physics.world

/**
 * Custom data holder for box2D body objects Userdata field
 */
class ObjectData(objName: String, var isFlaggedForDelete: Boolean, var id: String) {

  def getObjName: String = objName
  def getId: String = id
  def print(): Unit = println(s"objName: ${objName}, isFlaggedForDelete: ${isFlaggedForDelete}, id: ${id}")
  

}
