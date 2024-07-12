package com.rpg.game.systems.physics.physics_bodies.shapes

import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.maps.objects.PolylineMapObject
import com.badlogic.gdx.physics.box2d.{BodyDef, ChainShape, Fixture}
import com.rpg.entity.ObjectUserData
import com.rpg.game.systems.physics.physics_bodies.{PhysicsObjectBase, PhysicsObjectComplex, PhysicsObjectSimple}
import com.rpg.game.systems.physics.world.PhysicsObjectDefWrapper

class PolylineObject extends PhysicsObjectBase with PhysicsObjectSimple with PhysicsObjectComplex{

  override def getDefs(bodyType: BodyDef.BodyType, mapObject: MapObject): PhysicsObjectDefWrapper = {
    val polyLine = mapObject.asInstanceOf[PolylineMapObject].getPolyline
    val x = polyLine.getX
    val y = polyLine.getY

    val polyLineShape = new ChainShape()
    polyLineShape.createChain(polyLine.getVertices)

    val bodyDef = getBodyDef(x, y, bodyType)
    val fixtureDefOption = getFixtureDef(polyLineShape, bodyType)
    val objectUserData = ObjectUserData("Polyline", false, mapObject.getName)

    PhysicsObjectDefWrapper(bodyDef,fixtureDefOption,objectUserData)
  }

  override def getDefs(bodyType: BodyDef.BodyType, mapObject: MapObject, x: Float, y: Float): PhysicsObjectDefWrapper = {
    val polyLine = mapObject.asInstanceOf[PolylineMapObject].getPolyline

    val polyLineShape = new ChainShape()
    polyLineShape.createChain(polyLine.getVertices)

    val bodyDef = getBodyDef(x, y, bodyType)
    val fixtureDefOption = getFixtureDef(polyLineShape, bodyType)
    val objectUserData = ObjectUserData("Polyline", false, mapObject.getName)

    PhysicsObjectDefWrapper(bodyDef,fixtureDefOption,objectUserData)
  }

}
