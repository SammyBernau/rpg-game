package com.rpg.game.systems.physics.objects

import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.physics.box2d.{BodyDef, Fixture, FixtureDef}
import com.rpg.game.systems.physics.world.add.PhysicsObjectDefWrapper

trait PhysicsObjectSimple {
  
  def getDefs(bodyType: BodyType, mapObject: MapObject): PhysicsObjectDefWrapper
  
}
