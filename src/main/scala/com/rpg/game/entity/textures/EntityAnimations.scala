package com.rpg.game.entity.textures

import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.maps.tiled.{TiledMapTile, TiledMapTileSet}
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile
import com.rpg.game.game.config.CurrentWorld

import com.badlogic.gdx.graphics.g2d.{Animation, TextureRegion}
import scala.collection.mutable.ArrayBuffer
import scala.jdk.CollectionConverters.*

class EntityAnimations(currentWorld: CurrentWorld) {
  private val tileSets = currentWorld.tiledMap.getTileSets
  var frameDuration = .2f
  // .2f perfect walk
  //.5 perfect run

  object Player {
    //TODO -> This can be done better
    private val playerSpriteSheet: TiledMapTileSet = tileSets.getTileSet("npc_sprite")
    private val playerFrames = playerSpriteSheet.iterator().asScala
      .filter(tile => tile.getProperties.containsKey("type"))
      .toList
      .groupBy(tile => tile.getProperties.get("type").toString)

    private val frontFrames = playerFrames.getOrElse("front", List())
    private val rightFrames = playerFrames.getOrElse("right", List())
    private val backFrames = playerFrames.getOrElse("back", List())
    private val leftFrames = playerFrames.getOrElse("left", List())

    private val standFrames = playerFrames.getOrElse("stand", List())
    val standFramesSorted: Map[String, TiledMapTile] = standFrames
        .groupBy(tile => tile.getProperties.get("Name").toString)
      .view
      .mapValues(_.head)
      .toMap

    val frontAnimation = new Animation[TextureRegion](frameDuration,
      frontFrames.head.getTextureRegion,
      frontFrames(1).getTextureRegion)

    val rightAnimation = new Animation[TextureRegion](frameDuration,
      rightFrames.head.getTextureRegion,
      rightFrames(1).getTextureRegion)

    val backAnimation = new Animation[TextureRegion](frameDuration,
      backFrames.head.getTextureRegion,
      backFrames(1).getTextureRegion)

    val leftAnimation = new Animation[TextureRegion](frameDuration,
      leftFrames.head.getTextureRegion,
      leftFrames(1).getTextureRegion)
  }

}
