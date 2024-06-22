package com.rpg.game.game.util.rendering.fixture.shapes

import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.maps.objects.PolylineMapObject
import com.badlogic.gdx.physics.box2d.{BodyDef, ChainShape, Fixture}
import com.rpg.game.game.util.rendering.fixture.FixtureCreator

class PolylineObject extends FixtureCreator{

  override def getFixture(bodyType: BodyDef.BodyType, mapObject: MapObject, x: Float, y: Float): Fixture = {
    val polyLine = mapObject.asInstanceOf[PolylineMapObject].getPolyline
    val body = getBody(x, y, bodyType)

    val polyLineShape = new ChainShape()
    polyLineShape.createChain(polyLine.getVertices)

    val fixture = defineFixture(body, polyLineShape, bodyType)

    polyLineShape.dispose()

    fixture
  }

}
