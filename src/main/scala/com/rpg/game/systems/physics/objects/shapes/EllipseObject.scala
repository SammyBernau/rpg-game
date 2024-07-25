package com.rpg.game.systems.physics.objects.shapes

import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.maps.objects.{EllipseMapObject, TextureMapObject}
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.physics.box2d.{BodyDef, CircleShape, Fixture}
import com.rpg.game.systems.physics.objects.{PhysicsObjectBase, PhysicsObjectComplex, PhysicsObjectSimple}
import com.rpg.game.systems.physics.world.ObjectData
import com.rpg.game.systems.physics.world.add.PhysicsObjectDefWrapper

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
    val objectData = ObjectData("Ellipse", false, mapObject.getName)

    PhysicsObjectDefWrapper(circleShape,mapObject,bodyDef, fixtureDefOption, objectData)
  }

  override def getDefs(bodyType: BodyType, boundingBoxMapObject: MapObject, textureMapObject: TextureMapObject, x: Float, y: Float): PhysicsObjectDefWrapper = {
    val ellipse = boundingBoxMapObject.asInstanceOf[EllipseMapObject].getEllipse
    val width = ellipse.width / 2f
    val height = ellipse.height / 2f

    val circleShape = new CircleShape()
    circleShape.setRadius(width)

    val bodyDef = getBodyDef(x + width, y + height, bodyType)
    val fixtureDefOption = getFixtureDef(circleShape, bodyType)
    val objectData = ObjectData("Ellipse",false,boundingBoxMapObject.getName)
    
    PhysicsObjectDefWrapper(circleShape,textureMapObject,bodyDef,fixtureDefOption,objectData)
  }

}
