package com.rpg.game.systems.physics.objects.shapes

import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.maps.objects.{EllipseMapObject, PolylineMapObject, RectangleMapObject, TextureMapObject}
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.physics.box2d.{BodyDef, Fixture}
import com.rpg.game.systems.physics.objects.{PhysicsObjectBase, PhysicsObjectSimple}
import com.rpg.game.systems.physics.world.add.PhysicsObjectDefWrapper

class TextureObject extends PhysicsObjectBase with PhysicsObjectSimple {

  override def getDefs(bodyType: BodyType, mapObject: MapObject): PhysicsObjectDefWrapper = {
    val textureMapObject = mapObject.asInstanceOf[TextureMapObject]
    val x = textureMapObject.getX
    val y = textureMapObject.getY
    val tileObject = mapObject.asInstanceOf[TiledMapTileMapObject]
    val tile = tileObject.getTile
    val boundingBox = tile.getObjects.get(0)
    
    //Creates a physical bounding box with the location/dimensions from the bounding box of a texture defined in Tiled
    boundingBox match {
      case rectangleBoundingBox: RectangleMapObject =>
        RectangleObject().getDefs(bodyType, rectangleBoundingBox,textureMapObject, x, y)

      case ellipseBoundingBox: EllipseMapObject =>
        EllipseObject().getDefs(bodyType, ellipseBoundingBox, textureMapObject, x, y)

      case polylineBoundingBox: PolylineMapObject => //A polyline is a an incomplete polygon like a straight line used as a map boundary
        PolylineObjectBoundingBox().getDefs(bodyType, polylineBoundingBox, textureMapObject, x, y)
      case _ =>
        throw new IllegalStateException(s"No matching bounding_box found for ${tileObject.getName} with properties: ${tileObject.getProperties}")
    }
  }

}
