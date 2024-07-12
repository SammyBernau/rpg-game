package com.rpg.game.systems.physics.physics_bodies.shapes

import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.maps.objects.{EllipseMapObject, PolylineMapObject, RectangleMapObject, TextureMapObject}
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.physics.box2d.{BodyDef, Fixture}
import com.rpg.game.systems.physics.physics_bodies.{PhysicsObjectBase, PhysicsObjectSimple}
import com.rpg.game.systems.physics.world.PhysicsObjectDefWrapper

class TextureObject extends PhysicsObjectBase with PhysicsObjectSimple {

  override def getFixtureDef(bodyType: BodyType, mapObject: MapObject): PhysicsObjectDefWrapper = {
    val textureMapObject = mapObject.asInstanceOf[TextureMapObject]
    val x = textureMapObject.getX
    val y = textureMapObject.getY
    val tileObject = mapObject.asInstanceOf[TiledMapTileMapObject]
    val tile = tileObject.getTile
    val boundingBox = tile.getObjects.get(0)

    //Creates a physical bounding box with the location/dimensions from the bounding box of a texture defined in tiled
    boundingBox match {
      case rectangleMapObject: RectangleMapObject =>
        RectangleObject().getDefs(bodyType, rectangleMapObject, x, y)

      case ellipseMapObject: EllipseMapObject =>
        EllipseObject().getDefs(bodyType, ellipseMapObject, x, y)

      case polylineMapObject: PolylineMapObject => //A polyline is a an incomplete polygon like a straight line used as a map boundary
        PolylineObjectBoundingBox().getDefs(bodyType, polylineMapObject, x, y)
      case _ =>
        throw new IllegalStateException(s"No matching bounding_box found for ${tileObject.getName} with properties: ${tileObject.getProperties}")
    }
  }

}
