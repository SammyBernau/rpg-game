package com.rpg.game.entity.data

import com.badlogic.gdx.utils.{JsonReader, JsonValue}
import com.rpg.game.data.{DataAccess, DataAccessService}

import java.io.File


trait EntityAccess {
  //more of an entity parser using read and write on DataAccess
  def dataAccess: DataAccess[File,JsonValue]
  //get data from data access and locate data based off entity id

  //parser
  def getData(saveFile: File): Option[JsonValue] = dataAccess.read(saveFile)

  def getNonPlayerEntity(entityName: String): Option[JsonValue] = {
    ???
  }

  def getPlayerEntityData(entityId: String): Option[JsonValue] = {
    ???
  }


}
