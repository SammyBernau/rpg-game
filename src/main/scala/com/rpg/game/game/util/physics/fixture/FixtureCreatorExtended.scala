package com.rpg.game.game.util.physics.fixture

import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.physics.box2d.Fixture

trait FixtureCreatorExtended {
  def getFixture(bodyType: BodyType, mapObject: MapObject, x: Float, y: Float): Fixture
}
