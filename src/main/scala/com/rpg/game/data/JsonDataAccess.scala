package com.rpg.game.data

import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.utils.JsonValue
import com.google.inject.Inject


class JsonDataAccess extends DataAccess[JsonValue] {

  override def read(dataSource: String): Option[JsonValue] = {
    ???
  }
  override def write(dataSource: String, data: JsonValue): Boolean = {
    ???
  }

}
