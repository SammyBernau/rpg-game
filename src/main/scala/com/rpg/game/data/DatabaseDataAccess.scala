package com.rpg.game.data

import com.google.inject.Inject
import com.rpg.game.data.DataAccess
class DatabaseDataAccess extends DataAccess { //DataAccess[String] is a placeholder

  //TODO String is a placeholder for DataAccess[String,String]
  override def read(dataSource: String): DatabaseReadResult = ???

  override def write(dataSource: String, data: Any): Boolean = ???
}
