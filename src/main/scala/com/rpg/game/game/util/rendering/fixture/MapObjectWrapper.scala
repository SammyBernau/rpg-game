package com.rpg.game.game.util.rendering.fixture

import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.physics.box2d.Fixture

abstract class MapObjectWrapper(val mapObject: MapObject) {
  
  def getFixture(bodyType: BodyType, x: Float, y: Float): Fixture

}
