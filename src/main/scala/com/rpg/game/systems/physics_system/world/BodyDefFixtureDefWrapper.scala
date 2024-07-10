package com.rpg.game.systems.physics_system.world

import com.badlogic.gdx.physics.box2d.{BodyDef, FixtureDef}

case class BodyDefFixtureDefWrapper(body: BodyDef, fixtureDef: FixtureDef)
