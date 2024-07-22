package com.rpg.game.systems.physics.bodies.shapes

import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.maps.objects.{RectangleMapObject, TextureMapObject}
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.physics.box2d.{BodyDef, Fixture, PolygonShape}
import com.rpg.entity.ObjectUserData
import com.rpg.game.systems.physics.bodies.{PhysicsObjectBase, PhysicsObjectComplex, PhysicsObjectSimple}
import com.rpg.game.systems.physics.world.PhysicsObjectDefWrapper

class RectangleObject extends PhysicsObjectBase with PhysicsObjectSimple with PhysicsObjectComplex{

  override def getDefs(bodyType: BodyDef.BodyType, mapObject: MapObject): PhysicsObjectDefWrapper = {
    val rectangle = mapObject.asInstanceOf[RectangleMapObject].getRectangle
    val x = rectangle.getX
    val y = rectangle.getY
    val width = rectangle.getWidth / 2f
    val height = rectangle.getHeight / 2f



    val polygonShape = new PolygonShape()
    polygonShape.setAsBox(width, height)

    val bodyDef = getBodyDef(x + width, y + height, bodyType)
    val fixtureDefOption = getFixtureDef(polygonShape, bodyType)
    val objectUserData = ObjectUserData("Rectangle",false, mapObject.getName)
    
    PhysicsObjectDefWrapper(polygonShape, mapObject,bodyDef,fixtureDefOption,objectUserData)

  }

  override def getDefs(bodyType: BodyType, boundingBoxMapObject: MapObject, textureMapObject: TextureMapObject, x: Float, y: Float): PhysicsObjectDefWrapper = {
    val rectangle = boundingBoxMapObject.asInstanceOf[RectangleMapObject].getRectangle
    val width = rectangle.getWidth / 2f
    val height = rectangle.getHeight / 2f

    val polygonShape = new PolygonShape()
    polygonShape.setAsBox(width, height)
    
    val bodyDef = getBodyDef(x + width, y + height, bodyType)
    val fixtureDefOption = getFixtureDef(polygonShape, bodyType)
    val objectUserData = ObjectUserData("Rectangle",false,textureMapObject.getName)

    PhysicsObjectDefWrapper(polygonShape, textureMapObject,bodyDef,fixtureDefOption,objectUserData)
    
  }
  
  
}
