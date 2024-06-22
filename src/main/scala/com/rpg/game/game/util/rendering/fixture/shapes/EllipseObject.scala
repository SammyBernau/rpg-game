package com.rpg.game.game.util.rendering.fixture.shapes

import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.maps.objects.EllipseMapObject
import com.badlogic.gdx.physics.box2d.{BodyDef, CircleShape, Fixture}
import com.rpg.game.game.util.rendering.fixture.FixtureCreator

class EllipseObject extends FixtureCreator{

  override def getFixture(bodyType: BodyDef.BodyType, mapObject: MapObject, x: Float, y: Float): Fixture = {
    val ellipse = mapObject.asInstanceOf[EllipseMapObject].getEllipse
    
    val width = ellipse.width / 2f
    val height = ellipse.height / 2f
    val body = getBody(x + width, y + height, bodyType)

    val circleShape = new CircleShape()
    circleShape.setRadius(width)

    val fixture = defineFixture(body, circleShape, bodyType)

    circleShape.dispose()

    fixture
  }

}
