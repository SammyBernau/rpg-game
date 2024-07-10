package com.rpg.game.systems.physics_system.physics_bodies

import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.physics.box2d.Fixture

trait FixtureSimple {
  
  def getFixture(bodyType: BodyType, mapObject: MapObject): Fixture
  
}
