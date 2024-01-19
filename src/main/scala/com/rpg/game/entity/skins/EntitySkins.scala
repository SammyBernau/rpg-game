package com.rpg.game.entity.skins

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.{Animation, TextureRegion}

object EntitySkins {

  object Player {
    private val spriteFile: FileHandle = Gdx.files.internal("gfx/npc_blank.png")
    private val playerTextureSheet = new Texture(spriteFile)
    private val frameDuration = 0.3f

    //front
    val front = new TextureRegion(playerTextureSheet, 19,26,16,23)
    private val frontWalk = new TextureRegion(playerTextureSheet, 54,1,16,22)
    private val frontWalkTwo = new TextureRegion(playerTextureSheet,54,25,16,22)
    val frontAnimation = new Animation[TextureRegion](frameDuration,front,frontWalk,frontWalkTwo)

    //looking right
    private val right = new TextureRegion(playerTextureSheet,37,26,15,23)
    private val rightWalk = new TextureRegion(playerTextureSheet,89,1,15,22)
    private val rightWalkTwo = new TextureRegion(playerTextureSheet,89,25,15,22)
    val rightAnimation = new Animation[TextureRegion](frameDuration,right,rightWalk,rightWalkTwo)

    //looking left
    private val left = new TextureRegion(playerTextureSheet,37,1,15,23)
    private val leftWalk = new TextureRegion(playerTextureSheet,72,1,15,22)
    private val leftWalkTwo = new TextureRegion(playerTextureSheet,72,25,15,22)
    val leftAnimation = new Animation[TextureRegion](frameDuration,left,leftWalk,leftWalkTwo)

    //back
    private val back = new TextureRegion(playerTextureSheet,1,1,16,24)
    private val backWalk = new TextureRegion(playerTextureSheet,1,27,16,23)
    private val backWalkTwo = new TextureRegion(playerTextureSheet,19,1,16,23)
    val backAnimation = new Animation[TextureRegion](frameDuration, back,backWalk,backWalkTwo)

    


  }

}
