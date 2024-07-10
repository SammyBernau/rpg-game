package com.rpg.game.systems.physics_system.physics_bodies.shapes

import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.maps.objects.EllipseMapObject
import com.badlogic.gdx.physics.box2d.{BodyDef, CircleShape, Fixture}
import com.rpg.entity.ObjectUserData
import com.rpg.game.systems.physics_system.physics_bodies.{FixtureBase, FixtureExtended, FixtureSimple}

class EllipseObject extends FixtureSimple with FixtureExtended with FixtureBase{

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
    
    val userData = ObjectUserData("Ellipse",false,mapObject.getName)
    fixture.getBody.setUserData(userData)
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
    val userData = ObjectUserData("Ellipse",false,mapObject.getName)
    fixture.getBody.setUserData(userData)
    circleShape.dispose()

    fixture
  }

}
