package com.rpg.game.game.util.physics.fixture.shapes

import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.maps.objects.PolygonMapObject
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.physics.box2d.{Fixture, PolygonShape}
import com.rpg.game.game.util.physics.fixture.{FixtureBase, FixtureCreatorSimple}

class PolygonObject extends FixtureCreatorSimple with FixtureBase{

  override def getFixture(bodyType: BodyType, mapObject: MapObject): Fixture = {
    val polygon = mapObject.asInstanceOf[PolygonMapObject].getPolygon
    val x = polygon.getX
    val y = polygon.getY
    val body = getBody(x, y, bodyType)

    val polygonShape = new PolygonShape()
    polygonShape.set(polygon.getVertices)

    val fixture = defineFixture(body, polygonShape, bodyType)

    polygonShape.dispose()

    fixture
  }

}
