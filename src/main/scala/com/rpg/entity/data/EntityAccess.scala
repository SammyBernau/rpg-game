package com.rpg.entity.data

import com.badlogic.gdx.utils.{JsonReader, JsonValue}
import com.rpg.data.DataAccess

import java.io.File


trait EntityAccess {
  //more of an entity parser using read and write on DataAccess
  def dataAccess: DataAccess
  //get data from data access and locate data based off entity id

  //parser
  

  def getNonPlayerEntity(entityName: String): Option[JsonValue] = {
    ???
  }

  def getPlayerEntityData(entityId: String): Option[JsonValue] = {
    ???
  }


}
