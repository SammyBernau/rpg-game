package com.rpg.game.systems.physics_system.physics_bodies.shapes

import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.maps.objects.RectangleMapObject
import com.badlogic.gdx.physics.box2d.{BodyDef, Fixture, PolygonShape}
import com.rpg.entity.ObjectUserData
import com.rpg.game.systems.physics_system.physics_bodies.{FixtureBase, FixtureExtended, FixtureSimple}

class RectangleObject extends FixtureBase with FixtureSimple with FixtureExtended{

  override def getFixture(bodyType: BodyDef.BodyType, mapObject: MapObject): Fixture = {
    val rectangle = mapObject.asInstanceOf[RectangleMapObject].getRectangle
    val x = rectangle.getX
    val y = rectangle.getY
    val width = rectangle.getWidth / 2f
    val height = rectangle.getHeight / 2f

    val body = getBody(x + width, y + height, bodyType)


    val polygonShape = new PolygonShape()
    polygonShape.setAsBox(width, height)

    val fixture = defineFixture(body, polygonShape, bodyType)
    val userData = ObjectUserData("Rectangle",false, mapObject.getName)
    fixture.getBody.setUserData(userData)
    polygonShape.dispose()

    fixture
  }

  override def getFixture(bodyType: BodyDef.BodyType, mapObject: MapObject, x: Float, y: Float): Fixture = {
    val rectangle = mapObject.asInstanceOf[RectangleMapObject].getRectangle
    val width = rectangle.getWidth / 2f
    val height = rectangle.getHeight / 2f

    val body = getBody(x + width, y + height, bodyType)

    
    val polygonShape = new PolygonShape()
    polygonShape.setAsBox(width, height)

    val fixture = defineFixture(body, polygonShape, bodyType)
    val userData = ObjectUserData("Rectangle",false,mapObject.getName)
    fixture.getBody.setUserData(userData)
    polygonShape.dispose()

    fixture
  }
  
  
}
