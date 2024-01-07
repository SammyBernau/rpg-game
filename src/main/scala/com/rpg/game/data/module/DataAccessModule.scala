package com.rpg.game.data.module

import com.badlogic.gdx.utils.JsonValue
import com.google.inject.{AbstractModule, TypeLiteral}
import com.rpg.game.data.{DataAccess, DatabaseDataAccess, JsonDataAccess}

import java.io.File

class DataAccessModule extends AbstractModule {

  val client: String = "local" //TODO change this so that it detects somehow if client is booting for local play or multiplayer
  override def configure(): Unit = {
    if(client.contains("local")) configureJson()
    else configureDatabase()
  }

  private def configureDatabase(): Unit = bind(new TypeLiteral[DataAccess[String,String]]{}).to(classOf[DatabaseDataAccess])
  private def configureJson(): Unit = bind(new TypeLiteral[DataAccess[File,JsonValue]]{}).to(classOf[JsonDataAccess])



}
