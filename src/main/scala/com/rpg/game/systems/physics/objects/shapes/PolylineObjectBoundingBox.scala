package com.rpg.game.systems.physics.objects.shapes

import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.maps.objects.{PolylineMapObject, TextureMapObject}
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.physics.box2d.{BodyDef, ChainShape, Fixture}
import com.rpg.game.systems.physics.objects.{PhysicsObjectBase, PhysicsObjectComplex, PhysicsObjectSimple}
import com.rpg.game.systems.physics.world.ObjectData
import com.rpg.game.systems.physics.world.add.PhysicsObjectDefWrapper

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
    val objectData = ObjectData("Polyline", false, mapObject.getName)
        
    PhysicsObjectDefWrapper(polyLineShape, mapObject,bodyDef,fixtureDefOption,objectData)
  }

  override def getDefs(bodyType: BodyType, boundingBoxMapObject: MapObject, textureMapObject: TextureMapObject, x: Float, y: Float): PhysicsObjectDefWrapper = {
    val polyLine = boundingBoxMapObject.asInstanceOf[PolylineMapObject].getPolyline

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
    val objectData = ObjectData("Polyline", false, boundingBoxMapObject.getName)

    PhysicsObjectDefWrapper(polyLineShape, textureMapObject,bodyDef,fixtureDefOption,objectData)
    
  }
  

}
