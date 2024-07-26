package com.rpg.game.systems.physics.objects.shapes

import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.maps.objects.{PolylineMapObject, TextureMapObject}
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.physics.box2d.{BodyDef, ChainShape, Fixture}
import com.rpg.game.systems.physics.objects.{PhysicsObjectBase, PhysicsObjectComplex, PhysicsObjectSimple}
import com.rpg.game.systems.physics.world.ObjectData
import com.rpg.game.systems.physics.world.add.PhysicsObjectDefWrapper

class PolylineObject extends PhysicsObjectBase with PhysicsObjectSimple with PhysicsObjectComplex{

  private def createDefs(bodyType: BodyType, mapObject: PolylineMapObject, x: Float, y: Float): PhysicsObjectDefWrapper = {
    val polyLine = mapObject.getPolyline

    val polyLineShape = new ChainShape()
    polyLineShape.createChain(polyLine.getVertices)

    val bodyDef = getBodyDef(x, y, bodyType)
    val fixtureDefOption = getFixtureDef(polyLineShape, bodyType)
    val objectData = ObjectData("Polyline", false, mapObject.getName)

    PhysicsObjectDefWrapper(polyLineShape, mapObject, bodyDef, fixtureDefOption, objectData)
  }

  override def getDefs(bodyType: BodyType, mapObject: MapObject): PhysicsObjectDefWrapper = {
    val polyLineMapObject = mapObject.asInstanceOf[PolylineMapObject]
    val x = polyLineMapObject.getPolyline.getX
    val y = polyLineMapObject.getPolyline.getY
    createDefs(bodyType,polyLineMapObject,x,y)
  }

  override def getDefs(bodyType: BodyType, boundingBoxMapObject: MapObject, textureMapObject: TextureMapObject, x: Float, y: Float): PhysicsObjectDefWrapper = {
    val polyLineBoundingBoxMapObject = boundingBoxMapObject.asInstanceOf[PolylineMapObject]
    createDefs(bodyType,polyLineBoundingBoxMapObject,x,y).copy(mapObject = textureMapObject)
  }

}
