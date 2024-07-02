package com.rpg.entity

/**
 * Custom data holder for box2D body objects Userdata field
 */
class ObjectUserData(objName: String, var isFlaggedForDelete: Boolean, id: String) {

  def getObjName: String = objName
  def getId: String = id
  
  def print(): Unit = println(s"objName: ${objName}, isFlaggedForDelete: ${isFlaggedForDelete}, id: ${id}")
  

}
