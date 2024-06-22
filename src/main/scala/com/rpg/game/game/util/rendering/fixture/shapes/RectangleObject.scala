package com.rpg.game.game.util.rendering.fixture.shapes

import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.maps.objects.RectangleMapObject
import com.badlogic.gdx.physics.box2d.{BodyDef, Fixture, PolygonShape}
import com.rpg.game.game.util.rendering.fixture.FixtureCreator

class RectangleObject extends FixtureCreator{

  override def getFixture(bodyType: BodyDef.BodyType, mapObject: MapObject, x: Float, y: Float): Fixture = {
    val rectangle = mapObject.asInstanceOf[RectangleMapObject].getRectangle
    val width = rectangle.getWidth / 2f
    val height = rectangle.getHeight / 2f

    val body = getBody(x + width, y + height, bodyType)


    val polygonShape = new PolygonShape()
    polygonShape.setAsBox(width, height)

    val fixture = defineFixture(body, polygonShape, bodyType)

    polygonShape.dispose()

    fixture
  }
}
