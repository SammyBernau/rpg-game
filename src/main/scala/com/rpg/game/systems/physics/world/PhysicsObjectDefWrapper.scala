package com.rpg.game.systems.physics.world

import com.badlogic.gdx.physics.box2d.{BodyDef, FixtureDef}
import com.rpg.entity.ObjectUserData

case class PhysicsObjectDefWrapper(body: BodyDef, fixtureDef: Option[FixtureDef], objectUserData: ObjectUserData)
