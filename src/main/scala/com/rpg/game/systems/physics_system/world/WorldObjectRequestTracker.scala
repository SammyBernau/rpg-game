package com.rpg.game.systems.physics_system.world

import com.rpg.game.systems.FIFOTracker

import javax.inject.Singleton


/**
 * Wrapper class
 */
@Singleton
case class WorldObjectRequestTracker() {
  var worldObjectRequestTracker = new FIFOTracker[BodyDefFixtureDefWrapper]
}
