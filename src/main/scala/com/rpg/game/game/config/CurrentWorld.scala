package com.rpg.game.game.config

import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.badlogic.gdx.utils.viewport.Viewport
import com.rpg.game.game.OrthogonalTiledMapRendererWithObjects


/**
 * Houses current game settings and utils
 * @param viewport
 * @param mapRenderer
 * @param tiledMap
 * @param worldRenderer
 */
case class CurrentWorld(viewport: Viewport, mapRenderer: OrthogonalTiledMapRendererWithObjects, 
                        tiledMap: TiledMap, worldRenderer: Box2DDebugRenderer)
