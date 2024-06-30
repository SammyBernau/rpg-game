package com.rpg.game.game.util.physics.fixture.shapes

import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.maps.objects.EllipseMapObject
import com.badlogic.gdx.physics.box2d.{BodyDef, CircleShape, Fixture}
import com.rpg.game.game.util.physics.fixture.{FixtureBase, FixtureCreatorExtended, FixtureCreatorSimple}

class EllipseObject extends FixtureCreatorSimple with FixtureCreatorExtended with FixtureBase{

  override def getFixture(bodyType: BodyDef.BodyType, mapObject: MapObject): Fixture = {
    val ellipse = mapObject.asInstanceOf[EllipseMapObject].getEllipse
    val x = ellipse.x
    val y = ellipse.y

    val width = ellipse.width / 2f
    val height = ellipse.height / 2f
    val body = getBody(x + width, y + height, bodyType)

    val circleShape = new CircleShape()
    circleShape.setRadius(width)

    val fixture = defineFixture(body, circleShape, bodyType)

    circleShape.dispose()

    fixture
  }

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
