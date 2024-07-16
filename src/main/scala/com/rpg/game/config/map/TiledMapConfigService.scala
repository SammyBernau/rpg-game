package com.rpg.game.config.map

import com.badlogic.gdx.maps.tiled.{TiledMap, TiledMapTileLayer, TmxMapLoader}
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.rpg.game.config.ConfigService

class TiledMapConfigService(mapName: String) extends ConfigService {


  override def loadConfig(): TiledMapConfig = {
    val tiledMap = loadMap(mapName)
    val tileSize = getTileSize(tiledMap)
    val viewport = getViewport(tileSize)
    
    TiledMapConfig(tiledMap,tileSize, viewport)
  }

  private def loadMap(mapName: String): TiledMap = new TmxMapLoader().load(mapName)
  private def getTileSize(tiledMap: TiledMap): Int = tiledMap.getLayers.get(0).asInstanceOf[TiledMapTileLayer].getTileWidth
  private def getViewport(tileSize: Int): ExtendViewport = new ExtendViewport((30 * tileSize).toFloat, (20 * tileSize).toFloat)
  

}
