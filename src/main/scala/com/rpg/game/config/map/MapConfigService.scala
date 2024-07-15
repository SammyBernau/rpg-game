package com.rpg.game.config

import com.badlogic.gdx.maps.tiled.{TiledMap, TiledMapTileLayer, TmxMapLoader}
import com.badlogic.gdx.utils.viewport.ExtendViewport

class MapConfigService(mapName: String) extends ConfigService {


  override def loadConfig(): MapConfig = {
    val tiledMap = loadMap(mapName)
    val tileSize = getTileSize(tiledMap)
    val viewport = getViewport(tileSize)
    
    MapConfig(tiledMap,tileSize, viewport)
  }

  private def loadMap(mapName: String): TiledMap = new TmxMapLoader().load("assets/Tiled/Grassland.tmx")
  private def getTileSize(tiledMap: TiledMap): Int = tiledMap.getLayers.get(0).asInstanceOf[TiledMapTileLayer].getTileWidth
  private def getViewport(tileSize: Int): ExtendViewport = new ExtendViewport((30 * tileSize).toFloat, (20 * tileSize).toFloat)
  

}
