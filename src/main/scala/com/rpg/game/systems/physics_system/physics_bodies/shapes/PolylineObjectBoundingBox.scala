package com.rpg.game.systems.physics_system.physics_bodies.shapes

import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.maps.objects.PolylineMapObject
import com.badlogic.gdx.physics.box2d.{BodyDef, ChainShape, Fixture}
import com.rpg.entity.ObjectUserData
import com.rpg.game.systems.physics_system.physics_bodies.{FixtureBase, FixtureCreatorExtended, FixtureCreatorSimple}

class PolylineObjectBoundingBox extends FixtureBase with FixtureCreatorSimple with FixtureCreatorExtended{

  override def getFixture(bodyType: BodyDef.BodyType, mapObject: MapObject): Fixture = {
    val polyLine = mapObject.asInstanceOf[PolylineMapObject].getPolyline
    val x = polyLine.getX
    val y = polyLine.getY
    val body = getBody(x, y, bodyType)

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

    val fixture = defineFixture(body, polyLineShape, bodyType)
    val userData = ObjectUserData("Polyline", false, mapObject.getName)
    fixture.getBody.setUserData(userData)
    
    polyLineShape.dispose()
    
    fixture
  }

  override def getFixture(bodyType: BodyDef.BodyType, mapObject: MapObject, x: Float, y: Float): Fixture = {
    val polyLine = mapObject.asInstanceOf[PolylineMapObject].getPolyline
    val body = getBody(x, y, bodyType)

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

    val fixture = defineFixture(body, polyLineShape, bodyType)
    val userData = ObjectUserData("Polyline", false, mapObject.getName)
    fixture.getBody.setUserData(userData)
    polyLineShape.dispose()

    fixture
  }
  

}
