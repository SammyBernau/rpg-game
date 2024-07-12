package com.rpg.game.systems.physics.world

import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.physics.box2d.{BodyDef, FixtureDef, Shape}
import com.rpg.entity.ObjectUserData

case class PhysicsObjectDefWrapper(shape: Shape, mapObject: MapObject, body: BodyDef, maybeFixtureDef: Option[FixtureDef], objectUserData: ObjectUserData)
