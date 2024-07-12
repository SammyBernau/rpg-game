package com.rpg.game.systems.physics.physics_bodies

import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.maps.objects.{EllipseMapObject, PolygonMapObject, PolylineMapObject, RectangleMapObject, TextureMapObject}
import com.rpg.game.systems.physics.physics_bodies.shapes.{EllipseObject, PolygonObject, PolylineObject, PolylineObjectBoundingBox, RectangleObject, TextureObject}

object PhysicsObjectFactory {

  def create(mapObject: MapObject): PhysicsObjectSimple = mapObject match {
    case _: EllipseMapObject => new EllipseObject()
    case _: RectangleMapObject => new RectangleObject()
    case _: PolygonMapObject => new PolygonObject()
    case _: PolylineMapObject => new PolylineObject()
    case _: TextureMapObject => new TextureObject()
    case _ => throw new IllegalArgumentException(s"Unsupported MapObject type: ${mapObject.getName}")
  }

}
