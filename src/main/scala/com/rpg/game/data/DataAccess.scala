package com.rpg.game.data

trait DataAccess {
  
  //TODO figure out how these will work with a database and JSON
  //read
  def read(): Unit //Unit is a placeholder
  def write(): Boolean //maybe return success on write?
  //write

}
