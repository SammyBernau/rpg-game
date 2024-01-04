package com.rpg.game.data

trait DataAccess[T] {
  
  def read(dataSource: String): Option[T]
  def write(dataSource: String, data: T): Boolean //return success on write?

}
