package com.rpg.game.data.database

import com.rpg.game.data.{DataAccess, DataAccessFactory}

class DatabaseDataAccessFactory extends DataAccessFactory {

  override def create(name: String): DataAccess[Any, Any] = ???
}
