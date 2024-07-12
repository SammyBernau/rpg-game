package com.rpg.game.systems.rendering.services

import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.maps.objects.TextureMapObject
import com.badlogic.gdx.maps.tiled.TiledMap
import com.rpg.entity.ObjectUserData
import com.rpg.game.systems.physics.world.PhysicsObjectProducer

import javax.inject.Inject

class ObjectRenderingServiceHelper @Inject(gameObjectCache: GameObjectCache, physicsObjectProducer: PhysicsObjectProducer, map: TiledMap) {
  
  private val entityLayer = map.getLayers.get("entity").getObjects


  /**
   * This method was completed with help from https://lyze.dev/2021/03/25/libGDX-Tiled-Box2D-example/
   * Current implementation only parses objects that were pre-loaded on map
   */
  protected def parseObjectsFromMap(): Unit = {

    for (i <- 0 until entityLayer.getCount) {
      val obj = entityLayer.get(i)
      addGameObject(obj)
    }
  }

  /**
   * Dynamically added objects need to have their texture added to the entity layer in order to be rendered
   *
   * @param textureMapObject
   */
  def addToObjectLayer(textureMapObject: TextureMapObject): Unit = entityLayer.add(textureMapObject)


  def addGameObject(mapObject: MapObject): Unit = {
    entityLayer.add(mapObject)
    physicsObjectProducer.produceRequest(mapObject)
  }


  /**
   * Adds a new obj with its texture to the screen
   * This is used when you want to spawn a new object that was preloaded with the map, ie: bullets, enemies, etc.
   *
   * @param texture
   * @param obj
   */
  def addNewObjWithTexture(texture: TextureMapObject, obj: MapObject): Unit = {
    //        //Create collision box for new object
    //        addObject(obj)
    //
    //        //load texture into texture map
    //        addTextureMapObject(texture.getName, texture)
  }


  /**
   * Removes texture and fixture from above lists so they dont get rendered
   * Note: Removing the fixture from the fixture list doesn't delete the fixture, just decouples it from its respective texture
   *
   * @param name -> name as map key
   */
  def removeTexture(name: String): Unit = {
    val gameObject = gameObjectCache.getCache.getOrElse(name, null)

    val texture = gameObject.mapObject.asInstanceOf[TextureMapObject]
    val fixture = gameObject.fixture
    val userData = fixture.getBody.getUserData.asInstanceOf[ObjectUserData]
    if (userData != null && userData.isFlaggedForDelete) {
      entityLayer.remove(texture)
      gameObjectCache.remove(name)
    }
  }


}