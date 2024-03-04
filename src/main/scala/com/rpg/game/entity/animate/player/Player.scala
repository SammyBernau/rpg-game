package com.rpg.game.entity.animate.player

import com.artemis.PooledComponent
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.rpg.game.entity.Entity
import com.rpg.game.entity.animate.Humanoid
import com.rpg.game.entity.textures.TextureGrabber.PlayerTexture

case class Player(pId: Int,
             username: String,
             password: String,
             playerType: PlayerType, playerSettings: Humanoid) {
  
  //Overloaded constructor
}



//check for local or online play
//if local play dont ask for password and user?
