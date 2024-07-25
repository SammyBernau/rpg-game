package com.rpg.game.systems.physics.objects.shapes

import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.maps.objects.{PolylineMapObject, TextureMapObject}
import com.badlogic.gdx.physics.box2d.{BodyDef, ChainShape, Fixture}
import com.rpg.game.systems.physics.objects.{PhysicsObjectBase, PhysicsObjectComplex, PhysicsObjectSimple}
import com.rpg.game.systems.physics.world.ObjectData
import com.rpg.game.systems.physics.world.add.PhysicsObjectDefWrapper

class PolylineObject extends PhysicsObjectBase with PhysicsObjectSimple with PhysicsObjectComplex{

  override def getDefs(bodyType: BodyDef.BodyType, mapObject: MapObject): PhysicsObjectDefWrapper = {
    val polyLine = mapObject.asInstanceOf[PolylineMapObject].getPolyline
    val x = polyLine.getX
    val y = polyLine.getY

    val polyLineShape = new ChainShape()
    polyLineShape.createChain(polyLine.getVertices)

    val bodyDef = getBodyDef(x, y, bodyType)
    val fixtureDefOption = getFixtureDef(polyLineShape, bodyType)
    val objectData = ObjectData("Polyline", false, mapObject.getName)

    PhysicsObjectDefWrapper(polyLineShape, mapObject,bodyDef,fixtureDefOption,objectData)
  }

  override def getDefs(bodyType: BodyDef.BodyType, boundingBoxMapObject: MapObject, textureMapObject: TextureMapObject, x: Float, y: Float): PhysicsObjectDefWrapper = {
    val polyLine = boundingBoxMapObject.asInstanceOf[PolylineMapObject].getPolyline

    val polyLineShape = new ChainShape()
    polyLineShape.createChain(polyLine.getVertices)

    val bodyDef = getBodyDef(x, y, bodyType)
    val fixtureDefOption = getFixtureDef(polyLineShape, bodyType)
    val objectData = ObjectData("Polyline", false, boundingBoxMapObject.getName)

    PhysicsObjectDefWrapper(polyLineShape, textureMapObject,bodyDef,fixtureDefOption,objectData)
  }

}
