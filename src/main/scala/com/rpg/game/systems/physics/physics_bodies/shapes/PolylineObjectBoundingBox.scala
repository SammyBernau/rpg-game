package com.rpg.game.systems.physics.physics_bodies.shapes

import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.maps.objects.PolylineMapObject
import com.badlogic.gdx.physics.box2d.{BodyDef, ChainShape, Fixture}
import com.rpg.entity.ObjectUserData
import com.rpg.game.systems.physics.physics_bodies.{PhysicsObjectBase, PhysicsObjectComplex, PhysicsObjectSimple}
import com.rpg.game.systems.physics.world.PhysicsObjectDefWrapper

class PolylineObjectBoundingBox extends PhysicsObjectBase with PhysicsObjectSimple with PhysicsObjectComplex{

  override def getDefs(bodyType: BodyDef.BodyType, mapObject: MapObject): PhysicsObjectDefWrapper = {
    val polyLine = mapObject.asInstanceOf[PolylineMapObject].getPolyline
    val x = polyLine.getX
    val y = polyLine.getY

    val vertices = polyLine.getTransformedVertices
    val width = polyLine.getBoundingRectangle.getWidth
    val height = polyLine.getBoundingRectangle.getHeight
    val adjustedVertices = new Array[Float](polyLine.getVertices.length)

    for (i <- vertices.indices by 2) {
      adjustedVertices(i) = vertices(i) - (width / 2f)
      adjustedVertices(i + 1) = vertices(i + 1) - (height / 2f)
    }

    val polyLineShape = new ChainShape()
    polyLineShape.createChain(adjustedVertices)

    val bodyDef = getBodyDef(x, y, bodyType)
    val fixtureDefOption = getFixtureDef(polyLineShape, bodyType)
    val objectUserData = ObjectUserData("Polyline", false, mapObject.getName)
        
    PhysicsObjectDefWrapper(polyLineShape, mapObject,bodyDef,fixtureDefOption,objectUserData)
  }

  override def getDefs(bodyType: BodyDef.BodyType, mapObject: MapObject, x: Float, y: Float): PhysicsObjectDefWrapper = {
    val polyLine = mapObject.asInstanceOf[PolylineMapObject].getPolyline

    val vertices = polyLine.getTransformedVertices
    val width = polyLine.getBoundingRectangle.getWidth
    val height = polyLine.getBoundingRectangle.getHeight
    val adjustedVertices = new Array[Float](polyLine.getVertices.length)

    for (i <- vertices.indices by 2) {
      adjustedVertices(i) = vertices(i) - (width / 2f)
      adjustedVertices(i + 1) = vertices(i + 1) - (height / 2f)
    }

    val polyLineShape = new ChainShape()
    polyLineShape.createChain(adjustedVertices)

    val bodyDef = getBodyDef(x, y, bodyType)
    val fixtureDefOption = getFixtureDef(polyLineShape, bodyType)
    val objectUserData = ObjectUserData("Polyline", false, mapObject.getName)

    PhysicsObjectDefWrapper(polyLineShape, mapObject,bodyDef,fixtureDefOption,objectUserData)
    
  }
  

}
