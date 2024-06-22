package com.rpg.game.game.util.rendering.fixture

import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.physics.box2d.{Body, BodyDef, Fixture, FixtureDef, Shape}
import com.rpg.game.game.config.GameConfig.GameWorld.WORLD

trait FixtureCreatorSimple {
  
  def getFixture(bodyType: BodyType, mapObject: MapObject): Fixture
  
}
