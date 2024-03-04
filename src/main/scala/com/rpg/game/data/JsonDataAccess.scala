package com.rpg.game.data

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.utils.{JsonReader, JsonValue}
import com.google.inject.Inject
import com.rpg.game.data.DataAccess
import scala.util.{Try, Success, Failure}
import java.io.File

class JsonDataAccess extends DataAccess {
  
  private def jsonReader = new JsonReader
  override def read(dataSource: String): JsonReadResult = {
    ???
  }
  override def write(dataSource: String, data: Any): Boolean = {
    ???
  }

}
