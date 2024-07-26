//package com.rpg.game.systems.physics.objects.shapes
//
//import com.badlogic.gdx.maps.MapObject
//import com.badlogic.gdx.maps.objects.{RectangleMapObject, TextureMapObject}
//import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
//import com.badlogic.gdx.physics.box2d.{BodyDef, Fixture, PolygonShape}
//import com.rpg.game.systems.physics.objects.{PhysicsObjectBase, PhysicsObjectComplex, PhysicsObjectSimple}
//import com.rpg.game.systems.physics.world.ObjectData
//import com.rpg.game.systems.physics.world.add.PhysicsObjectDefWrapper
//
//class RectangleObject extends PhysicsObjectBase with PhysicsObjectSimple with PhysicsObjectComplex{
//
//  override def getDefs(bodyType: BodyDef.BodyType, mapObject: MapObject): PhysicsObjectDefWrapper = {
//    val rectangle = mapObject.asInstanceOf[RectangleMapObject].getRectangle
//    val x = rectangle.getX
//    val y = rectangle.getY
//    val width = rectangle.getWidth / 2f
//    val height = rectangle.getHeight / 2f
//
//
//
//    val polygonShape = new PolygonShape()
//    polygonShape.setAsBox(width, height)
//
//    val bodyDef = getBodyDef(x + width, y + height, bodyType)
//    val fixtureDefOption = getFixtureDef(polygonShape, bodyType)
//    val objectData = ObjectData("Rectangle",false, mapObject.getName)
//
//    PhysicsObjectDefWrapper(polygonShape, mapObject,bodyDef,fixtureDefOption,objectData)
//
//  }
//
//  override def getDefs(bodyType: BodyType, boundingBoxMapObject: MapObject, textureMapObject: TextureMapObject, x: Float, y: Float): PhysicsObjectDefWrapper = {
//    val rectangle = boundingBoxMapObject.asInstanceOf[RectangleMapObject].getRectangle
//    val width = rectangle.getWidth / 2f
//    val height = rectangle.getHeight / 2f
//
//    val polygonShape = new PolygonShape()
//    polygonShape.setAsBox(width, height)
//
//    val bodyDef = getBodyDef(x + width, y + height, bodyType)
//    val fixtureDefOption = getFixtureDef(polygonShape, bodyType)
//    val objectData = ObjectData("Rectangle",false,textureMapObject.getName)
//
//    PhysicsObjectDefWrapper(polygonShape, textureMapObject,bodyDef,fixtureDefOption,objectData)
//
//  }
//
//
//
//
//}

//package com.rpg.game.systems.physics.objects.shapes
//
//import com.badlogic.gdx.maps.MapObject
//import com.badlogic.gdx.maps.objects.{RectangleMapObject, TextureMapObject}
//import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
//import com.badlogic.gdx.physics.box2d.{BodyDef, Fixture, PolygonShape}
//import com.rpg.game.systems.physics.objects.{PhysicsObjectBase, PhysicsObjectComplex, PhysicsObjectSimple}
//import com.rpg.game.systems.physics.world.ObjectData
//import com.rpg.game.systems.physics.world.add.PhysicsObjectDefWrapper
//
//class RectangleObject extends PhysicsObjectBase with PhysicsObjectSimple with PhysicsObjectComplex{
//
//  private def createDefs(bodyType: BodyType, mapObject: MapObject, x: Float, y: Float, mapObj: MapObject): PhysicsObjectDefWrapper = {
//    val rectangle = mapObject.asInstanceOf[RectangleMapObject].getRectangle
//    val width = rectangle.getWidth / 2f
//    val height = rectangle.getHeight / 2f
//
//    val polygonShape = new PolygonShape()
//    polygonShape.setAsBox(width, height)
//
//    val bodyDef = getBodyDef(x + width, y + height, bodyType)
//    val fixtureDefOption = getFixtureDef(polygonShape, bodyType)
//    val objectData = ObjectData("Rectangle", false, mapObj.getName)
//
//    PhysicsObjectDefWrapper(polygonShape, mapObj, bodyDef, fixtureDefOption, objectData)
//  }
//
//  override def getDefs(bodyType: BodyType, mapObject: MapObject): PhysicsObjectDefWrapper = {
//    val x = mapObject.asInstanceOf[RectangleMapObject].getRectangle.getX
//    val y = mapObject.asInstanceOf[RectangleMapObject].getRectangle.getY
//    createDefs(bodyType, mapObject, x, y, mapObject)
//  }
//
//  override def getDefs(bodyType: BodyType, boundingBoxMapObject: MapObject, textureMapObject: TextureMapObject, x: Float, y: Float): PhysicsObjectDefWrapper = {
//    createDefs(bodyType, boundingBoxMapObject, x, y, textureMapObject)
//  }
//}


package com.rpg.game.systems.physics.objects.shapes

import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.maps.objects.{RectangleMapObject, TextureMapObject}
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.physics.box2d.{BodyDef, Fixture, PolygonShape}
import com.rpg.game.systems.physics.objects.{PhysicsObjectBase, PhysicsObjectComplex, PhysicsObjectSimple}
import com.rpg.game.systems.physics.world.ObjectData
import com.rpg.game.systems.physics.world.add.PhysicsObjectDefWrapper

class RectangleObject extends PhysicsObjectBase with PhysicsObjectSimple with PhysicsObjectComplex {

  private def createDefs(bodyType: BodyType, mapObject: RectangleMapObject, x: Float, y: Float): PhysicsObjectDefWrapper = {
    val rectangle = mapObject.getRectangle
    val width = rectangle.getWidth / 2f
    val height = rectangle.getHeight / 2f

    val polygonShape = new PolygonShape()
    polygonShape.setAsBox(width, height)

    val bodyDef = getBodyDef(x + width, y + height, bodyType)
    val fixtureDefOption = getFixtureDef(polygonShape, bodyType)
    val objectData = ObjectData("Rectangle", false, mapObject.getName)

    PhysicsObjectDefWrapper(polygonShape, mapObject, bodyDef, fixtureDefOption, objectData)
  }

  override def getDefs(bodyType: BodyType, mapObject: MapObject): PhysicsObjectDefWrapper = {
    val rectangleMapObject = mapObject.asInstanceOf[RectangleMapObject]
    val x = rectangleMapObject.getRectangle.getX
    val y = rectangleMapObject.getRectangle.getY
    createDefs(bodyType, rectangleMapObject, x, y)
  }

  override def getDefs(bodyType: BodyType, boundingBoxMapObject: MapObject, textureMapObject: TextureMapObject, x: Float, y: Float): PhysicsObjectDefWrapper = {
    val rectangleBoundingBoxMapObject = boundingBoxMapObject.asInstanceOf[RectangleMapObject]
    createDefs(bodyType, rectangleBoundingBoxMapObject, x, y).copy(mapObject = textureMapObject)
  }
}

