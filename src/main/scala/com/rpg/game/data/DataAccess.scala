package com.rpg.game.data

trait DataAccess {
  
  //read
  def read(): Unit //Unit is a placeholder
  def write(): Boolean //maybe return success on write?
  //write

}
