package com.rpg.game.entity.skins

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion

object EntitySkins {

  object Player {
    private val spriteFile: FileHandle = Gdx.files.internal("gfx/NPC_test.png")
    private val playerTextureSheet = new Texture(spriteFile)

    //val frontFacingSprite = new TextureRegion(playerTextureSheet, 0,0,15,32)
    val frontFacingWalkingSprite = new TextureRegion(playerTextureSheet, 0,1,14,20)
    val backFacingSprite = new TextureRegion(playerTextureSheet,2,0,14,22)


    val sideWaysSprite = new TextureRegion(playerTextureSheet, 2,0,16,32)
  }

}
