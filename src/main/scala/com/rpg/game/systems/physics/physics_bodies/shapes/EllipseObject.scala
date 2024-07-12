package com.rpg.game.systems.physics.physics_bodies.shapes

import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.maps.objects.EllipseMapObject
import com.badlogic.gdx.physics.box2d.{BodyDef, CircleShape, Fixture}
import com.rpg.entity.ObjectUserData
import com.rpg.game.systems.physics.physics_bodies.{PhysicsObjectBase, PhysicsObjectComplex, PhysicsObjectSimple}
import com.rpg.game.systems.physics.world.PhysicsObjectDefWrapper

class EllipseObject extends PhysicsObjectSimple with PhysicsObjectComplex with PhysicsObjectBase{

  override def getDefs(bodyType: BodyDef.BodyType, mapObject: MapObject): PhysicsObjectDefWrapper = {
    val ellipse = mapObject.asInstanceOf[EllipseMapObject].getEllipse
    val x = ellipse.x
    val y = ellipse.y

    val width = ellipse.width / 2f
    val height = ellipse.height / 2f

    val circleShape = new CircleShape()
    circleShape.setRadius(width)

    val bodyDef = getBodyDef(x + width, y + height, bodyType)
    val fixtureDefOption = getFixtureDef(circleShape, bodyType)
    val objectUserData = ObjectUserData("Ellipse", false, mapObject.getName)

    PhysicsObjectDefWrapper(circleShape,mapObject,bodyDef, fixtureDefOption, objectUserData)
  }

  override def getDefs(bodyType: BodyDef.BodyType, mapObject: MapObject, x: Float, y: Float): PhysicsObjectDefWrapper = {
    val ellipse = mapObject.asInstanceOf[EllipseMapObject].getEllipse
    val width = ellipse.width / 2f
    val height = ellipse.height / 2f

    val circleShape = new CircleShape()
    circleShape.setRadius(width)

    val bodyDef = getBodyDef(x + width, y + height, bodyType)
    val fixtureDefOption = getFixtureDef(circleShape, bodyType)
    val objectUserData = ObjectUserData("Ellipse",false,mapObject.getName)
    
    PhysicsObjectDefWrapper(circleShape,mapObject,bodyDef,fixtureDefOption,objectUserData)
  }

}
