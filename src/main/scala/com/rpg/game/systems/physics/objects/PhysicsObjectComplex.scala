package com.rpg.game.systems.physics.objects

import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.maps.objects.TextureMapObject
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.physics.box2d.Fixture
import com.rpg.game.systems.physics.world.add.PhysicsObjectDefWrapper

trait PhysicsObjectComplex {
  def getDefs(bodyType: BodyType, boundingBoxMapObject: MapObject, textureMapObject: TextureMapObject, x: Float, y: Float): PhysicsObjectDefWrapper
}
