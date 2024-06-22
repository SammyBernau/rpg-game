package com.rpg.game.game.util.rendering.fixture.shapes

import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.maps.objects.PolygonMapObject
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.physics.box2d.{Fixture, PolygonShape}
import com.rpg.game.game.util.rendering.fixture.FixtureCreator

class PolygonObject extends FixtureCreator{

  override def getFixture(bodyType: BodyType, mapObject: MapObject, x: Float, y: Float): Fixture = {
    val polygon = mapObject.asInstanceOf[PolygonMapObject].getPolygon

    val body = getBody(x, y, bodyType)

    val polygonShape = new PolygonShape()
    polygonShape.set(polygon.getVertices)

    val fixture = defineFixture(body, polygonShape, bodyType)

    polygonShape.dispose()

    fixture
  }

}
