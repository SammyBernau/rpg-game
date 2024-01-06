package com.rpg.game.data.json

import com.rpg.game.data.{DataAccess, DataAccessFactory}

class JsonDataAccessFactory extends DataAccessFactory{

  //params: File
  override def create(name: String): DataAccess[Any, Any] = ???

}
