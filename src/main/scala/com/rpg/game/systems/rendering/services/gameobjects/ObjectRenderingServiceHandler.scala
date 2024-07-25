package com.rpg.game.systems.rendering.services.gameobjects

import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.maps.objects.TextureMapObject
import com.badlogic.gdx.maps.tiled.TiledMap
import com.rpg.game.systems.physics.world.ObjectData
import com.rpg.game.systems.physics.world.add.PhysicsObjectProducer

import javax.inject.Inject

/**
 * Handles object creation requests, parsing of preloaded objects, and removal of objects from world
 * @param gameObjectCache -> List of current physics objects
 * @param physicsObjectProducer -> Creates requests for objects to be made on main thread
 * @param map -> Game map
 */

class ObjectRenderingServiceHandler @Inject(gameObjectCache: GameObjectCache, physicsObjectProducer: PhysicsObjectProducer, map: TiledMap) {
  
  private val entityLayer = map.getLayers.get("entity").getObjects


  /**
   * This method was completed with help from https://lyze.dev/2021/03/25/libGDX-Tiled-Box2D-example/
   * Current implementation only parses objects that were pre-loaded on map
   */
  def parseObjectsFromMap(): Unit = {

    for (i <- 0 until entityLayer.getCount) {
      val obj = entityLayer.get(i)
      addGameObject(obj)
    }
  }

  /**
   * Dynamically added objects need to have their texture added to the entity layer in order to be rendered
   *
   * @param mapObject -> object
   */
  private def addToObjectLayer(mapObject: MapObject): Unit = entityLayer.add(mapObject)


  def addGameObject(mapObject: MapObject): Unit = {
    physicsObjectProducer.produce(mapObject)
    addToObjectLayer(mapObject)
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
    val userData = fixture.getBody.getUserData.asInstanceOf[ObjectData]
    if (userData != null && userData.isFlaggedForDelete) {
      entityLayer.remove(texture)
      gameObjectCache.remove(name)
    }
  }


}
