package com.rpg.game.data.database

import com.google.inject.Inject
import com.rpg.game.data.DataAccess
class DatabaseDataAccess extends DataAccess[String,String] { //DataAccess[String] is a placeholder

  //TODO String is a placeholder for DataAccess[String,String]
  override def read(dataSource: String): Option[String] = ???

  override def write(dataSource: String, data: String): Boolean = ???
}
