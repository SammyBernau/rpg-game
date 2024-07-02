package com.rpg.data.database

import javax.inject.{Inject, Named, Provider}
import com.rpg.data.database.DatabaseConstants.DBOptions


case class DatabaseConfiguration(name: String,
                                 host: String,
                                 port: Int,
                                 user: String,
                                 password: String,
                                 poolSize: Int)


class DatabaseConfigurationProvider @Inject()(@Named(DBOptions.Name.ConfigKey) dbName: String,
                                              @Named(DBOptions.Host.ConfigKey) hostAddress: String,
                                              @Named(DBOptions.Username.ConfigKey) dbUsername: String,
                                              @Named(DBOptions.Password.ConfigKey) dbPassword: String,
                                              @Named(DBOptions.Port.ConfigKey) dbPort: Int,
                                              @Named(DBOptions.PoolSize.ConfigKey) poolSize: Int) extends Provider[DatabaseConfiguration] {

  override def get(): DatabaseConfiguration = DatabaseConfiguration(dbName,hostAddress,dbPort,dbUsername,dbPassword, poolSize)
}

 
