package com.rpg.game.data

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.utils.{JsonReader, JsonValue}
import com.google.inject.Inject
import com.rpg.game.data.DataAccess
import scala.util.{Try, Success, Failure}
import java.io.File

class JsonDataAccess extends DataAccess[File,JsonValue] {
  
  private def jsonReader = new JsonReader
  override def read(dataSource: File): Option[JsonValue] = {
    Try (jsonReader.parse(Gdx.files.internal(dataSource.getPath))) match {
        case Success(jsonValue) => Some(jsonValue)
        case Failure(exception) =>
          println(s"Failed to parse JSON save file ${dataSource.getPath}: ${exception.getMessage}")
          None
      }
  }
  override def write(dataSource: File, data: JsonValue): Boolean = {
    ???
  }

}
