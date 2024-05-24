package com.rpg.game.game.config

import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.badlogic.gdx.utils.viewport.Viewport
import com.rpg.game.game.util.rendering.OrthogonalTiledMapRendererWithObjects


/**
 * Houses current game settings and utils
 *
 * @param viewport -> current viewport of game
 * @param mapRenderer -> current map renderer
 * @param tiledMap -> current Tiled map
 * @param worldRenderer -> current world renderer
 * @author Sam Bernau
 */

//TODO -> change these to global var via object file 
case class CurrentWorld(viewport: Viewport, mapRenderer: OrthogonalTiledMapRendererWithObjects, 
                        tiledMap: TiledMap, worldRenderer: Box2DDebugRenderer)
