package com.rpg.game.data.module

import com.badlogic.gdx.utils.JsonValue
import com.google.inject.AbstractModule
import com.rpg.game.data.database.DatabaseDataAccess
import com.rpg.game.data.DataAccess
import com.rpg.game.data.json.JsonDataAccess

import java.io.File

class DataAccessModule extends AbstractModule {

  val client: String = "local" //TODO change this so that it detects somehow if client is booting for local play or multiplayer
  override def configure(): Unit = {
    if(client.contains("local")) configureJson()
    else configureDatabase()
  }

  private def configureDatabase(): Unit = bind(classOf[DataAccess[String,String]]).to(classOf[DatabaseDataAccess])
  private def configureJson(): Unit = bind(classOf[DataAccess[File,JsonValue]]).to(classOf[JsonDataAccess])



}
