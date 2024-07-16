package com.rpg.game.config.map

import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.rpg.game.config.Config

case class TiledMapConfig(tiledMap: TiledMap, tileSize: Int, viewport: ExtendViewport) extends Config
