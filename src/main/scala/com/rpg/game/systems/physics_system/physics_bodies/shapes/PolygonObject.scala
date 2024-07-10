package com.rpg.game.systems.physics_system.physics_bodies.shapes

import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.maps.objects.PolygonMapObject
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.physics.box2d.{Fixture, PolygonShape}
import com.rpg.entity.ObjectUserData
import com.rpg.game.systems.physics_system.physics_bodies.{FixtureBase, FixtureSimple}

class PolygonObject extends FixtureSimple with FixtureBase{

  override def getFixture(bodyType: BodyType, mapObject: MapObject): Fixture = {
    val polygon = mapObject.asInstanceOf[PolygonMapObject].getPolygon
    val x = polygon.getX
    val y = polygon.getY
    val body = getBody(x, y, bodyType)

    val polygonShape = new PolygonShape()
    polygonShape.set(polygon.getVertices)

    val fixture = defineFixture(body, polygonShape, bodyType)
    val userData = ObjectUserData("Polygon", false, mapObject.getName)
    fixture.getBody.setUserData(userData)

    polygonShape.dispose()

    fixture
  }

}
