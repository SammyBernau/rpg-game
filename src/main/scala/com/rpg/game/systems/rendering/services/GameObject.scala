package com.rpg.game.systems.rendering.services

import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.maps.objects.TextureMapObject
import com.badlogic.gdx.physics.box2d.Fixture

case class GameObject(mapObject: MapObject, fixture: Fixture)