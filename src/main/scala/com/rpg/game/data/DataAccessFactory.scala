package com.rpg.game.data

import com.badlogic.gdx.utils.JsonValue
import com.google.inject.Inject

import java.io.File
import javax.inject.Provider

//Decides what factory is supposed to be provided
trait DataAccessFactory {
  def create(name: String): DataAccess[Any,Any]

}
