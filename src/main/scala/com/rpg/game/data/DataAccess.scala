package com.rpg.game.data

trait DataAccess {
  
  //read
  def read(): Unit
  def write(): Boolean
  //write

}
