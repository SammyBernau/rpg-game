package com.rpg.game.systems.physics.physics_bodies

import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.physics.box2d.Fixture

trait PhysicsObjectComplex {
  def getDefs(bodyType: BodyType, mapObject: MapObject, x: Float, y: Float): Fixture
}
