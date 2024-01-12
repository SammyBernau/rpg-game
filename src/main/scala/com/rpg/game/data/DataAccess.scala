package com.rpg.game.data


trait DataAccess[Source,Output] {
  def read(dataSource: Source): Option[Output]
  def write(dataSource: Source, data: Output): Boolean //return success on write?

}
