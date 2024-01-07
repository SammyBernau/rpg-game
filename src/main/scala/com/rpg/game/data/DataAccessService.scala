package com.rpg.game.data

import javax.inject.Inject


//redundant implementation as nothing new is added
//TODO discover a better solution if nothing new is added
class DataAccessService[Source,Output] @Inject()(dataAccess: DataAccess[Source,Output]) {
  
  def read(dataSource: Source): Option[Output] = dataAccess.read(dataSource)
  def write(dataSource: Source, data: Output): Boolean = dataAccess.write(dataSource,data)

}
