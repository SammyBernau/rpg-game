package com.rpg.game.config

import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.utils.viewport.ExtendViewport

case class MapConfig(tiledMap: TiledMap, tileSize: Int, viewport: ExtendViewport) extends Config
