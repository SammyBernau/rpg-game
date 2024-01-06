package com.rpg.game.data.json

import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.utils.JsonValue
import com.google.inject.Inject
import com.rpg.game.data.DataAccess

import java.io.File


class JsonDataAccess extends DataAccess[File,JsonValue] {

  override def read(dataSource: File): Option[JsonValue] = {
    ???
  }
  override def write(dataSource: File, data: JsonValue): Boolean = {
    ???
  }

}
