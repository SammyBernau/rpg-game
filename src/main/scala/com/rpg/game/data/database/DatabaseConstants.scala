package com.rpg.game.data.database


object DatabaseConstants{
  object DBOptions {
    object Name extends DBPath {
      final val ConfigKey = "db.name"
    }
    
    object Host extends DBPath {
      final val ConfigKey = "db.host"
    }
    
    object Username extends DBPath {
      final val ConfigKey = "db.username"
    }

    object Password extends DBPath {
      final val ConfigKey = "db.password"
    }

    object Port extends DBPath {
      final val ConfigKey = "db.port"
    }

    object PoolSize extends DBPath {
      final val ConfigKey = "db.poolsize"
    }
  }
}

trait DBPath {
  val ConfigKey: String
}
 
