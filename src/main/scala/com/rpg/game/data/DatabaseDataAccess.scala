package com.rpg.game.data

import com.google.inject.Inject

class DatabaseDataAccess extends DataAccess[String] { //DataAccess[String] is a placeholder

  //TODO String is a placeholder for DataAccess[String]
  override def read(dataSource: String): Option[String] = {
    ???
  }
  override def write(dataSource: String, data: String): Boolean = {
    ???
  }
}
