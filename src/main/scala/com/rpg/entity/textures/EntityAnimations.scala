package com.rpg.entity.textures

import com.badlogic.gdx.graphics.g2d.Animation.PlayMode
import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.maps.tiled.{TiledMapTile, TiledMapTileSet, TmxMapLoader}
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile
import com.rpg.game.config.CurrentSettings
import com.badlogic.gdx.graphics.g2d.{Animation, TextureRegion}

import scala.collection.mutable.ArrayBuffer
import scala.jdk.CollectionConverters.*

class EntityAnimations(currentWorld: CurrentSettings) {
  private val preLoadedTileSets = currentWorld.tiledMap.getTileSets
  var frameDuration = .2f
  var dodgeFrameDuration = .5f
  // .2f perfect walk
  //.5 perfect run

  private def sortFramesByOrder(frames: List[TiledMapTile]): List[TiledMapTile] = {
    frames.sortWith((tile1, tile2) => {
      val order1 = tile1.getProperties.get("Order").toString.toInt
      val order2 = tile2.getProperties.get("Order").toString.toInt
      order1 < order2
    })
  }

  private def getFramesAsMap(spriteSheet: TiledMapTileSet): Map[String, List[TiledMapTile]] = {
    spriteSheet.iterator().asScala
      .filter(tile => tile.getProperties.containsKey("type"))
      .toList
      .groupBy(tile => tile.getProperties.get("type").toString)
  }

  object Player {
    //TODO -> This can be done better
    private val playerSpriteSheet: TiledMapTileSet = preLoadedTileSets.getTileSet("PlayerSpriteV2")
    private val playerFrames = getFramesAsMap(playerSpriteSheet)

    private val frontFrames = playerFrames.getOrElse("front", List())
    private val rightFrames = playerFrames.getOrElse("right", List())
    private val backFrames = playerFrames.getOrElse("back", List())
    private val leftFrames = playerFrames.getOrElse("left", List())

    private val frontDodgeFrames = playerFrames.getOrElse("front_dodge", List())
    private val leftDodgeFrames = playerFrames.getOrElse("left_dodge", List())
    private val rightDodgeFrames = playerFrames.getOrElse("right_dodge", List())
    private val backDodgeFrames = playerFrames.getOrElse("back_dodge", List())


    private val standFrames = playerFrames.getOrElse("stand", List())
    val standFramesSorted: Map[String, TiledMapTile] = standFrames
        .groupBy(tile => tile.getProperties.get("Name").toString)
      .view
      .mapValues(_.head)
      .toMap

    //TODO -> change this to a for loop for each so it doesnt hard code it
    val frontAnimation = new Animation[TextureRegion](frameDuration,
      frontFrames.head.getTextureRegion,
      frontFrames(1).getTextureRegion)

    val frontDodgeAnimation = new Animation[TextureRegion](dodgeFrameDuration,
      frontDodgeFrames.head.getTextureRegion,
      frontDodgeFrames(1).getTextureRegion,
      frontDodgeFrames(2).getTextureRegion)

    val rightAnimation = new Animation[TextureRegion](frameDuration,
      rightFrames.head.getTextureRegion,
      rightFrames(1).getTextureRegion)
    
    val rightDodgeAnimation = new Animation[TextureRegion](dodgeFrameDuration,
      rightDodgeFrames.head.getTextureRegion,
      rightDodgeFrames(1).getTextureRegion,
      rightDodgeFrames(2).getTextureRegion)
    
    val backAnimation = new Animation[TextureRegion](frameDuration,
    backFrames.head.getTextureRegion,
    backFrames(1).getTextureRegion)
    
    val backDodgeAnimation = new Animation[TextureRegion](dodgeFrameDuration,
      backDodgeFrames.head.getTextureRegion,
      backDodgeFrames(1).getTextureRegion,
      backDodgeFrames(2).getTextureRegion)
    
    val leftAnimation = new Animation[TextureRegion](frameDuration,
    leftFrames.head.getTextureRegion,
    leftFrames(1).getTextureRegion)
    
    val leftDodgeAnimation = new Animation[TextureRegion](dodgeFrameDuration,
      leftDodgeFrames.head.getTextureRegion,
      leftDodgeFrames(1).getTextureRegion,
      leftDodgeFrames(2).getTextureRegion)
  }



  //The following code is for TileSets that do not belong to a legitimate map (aka one that the player is meant to see).
  // Another map had to be created (SpriteUtils.tmx) in order for tilesets to be loaded that aren't yet used by the main game maps
  //Stupid work around until another solution is found to load .tsx files individually that arent preloaded
  private val nonPreLoadedTileSets = new TmxMapLoader().load("assets/Tiled/SpriteUtils.tmx").getTileSets
  object GhostFireBall {
    private val ghostFireballSpriteSheet: TiledMapTileSet = nonPreLoadedTileSets.getTileSet("GhostFireball_projectile")
    private val frames = getFramesAsMap(ghostFireballSpriteSheet)
    val tile: TiledMapTile = frames.getOrElse("ghost_fireball",List()).head
    val textureRegion: TextureRegion = tile.getTextureRegion

  }

}
