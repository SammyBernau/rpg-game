package com.rpg.game.systems.physics.objects.shapes

import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.maps.objects.PolygonMapObject
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.physics.box2d.{Fixture, PolygonShape}
import com.rpg.game.systems.physics.objects.{PhysicsObjectBase, PhysicsObjectSimple}
import com.rpg.game.systems.physics.world.ObjectData
import com.rpg.game.systems.physics.world.add.PhysicsObjectDefWrapper

class PolygonObject extends PhysicsObjectSimple with PhysicsObjectBase{

  override def getDefs(bodyType: BodyType, mapObject: MapObject): PhysicsObjectDefWrapper = {
    val polygon = mapObject.asInstanceOf[PolygonMapObject].getPolygon
    val x = polygon.getX
    val y = polygon.getY

    val polygonShape = new PolygonShape()
    polygonShape.set(polygon.getVertices)

    val fixtureDefOption = getFixtureDef(polygonShape, bodyType)
    val bodyDef = getBodyDef(x, y, bodyType)
    val objectData = ObjectData("Polygon", false, mapObject.getName)

    PhysicsObjectDefWrapper(polygonShape, mapObject,bodyDef, fixtureDefOption, objectData)


  }
  

}
