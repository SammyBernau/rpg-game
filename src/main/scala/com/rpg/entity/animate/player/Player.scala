package com.rpg.entity.animate.player

import com.artemis.PooledComponent
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.rpg.entity.Entity
import com.rpg.entity.animate.entityconstructs.Humanoid

case class Player(pId: Int,
             username: String,
             password: String,
             playerType: PlayerType, playerSettings: Humanoid) {
  
  
  //Overloaded constructor
  
  
}



//check for local or online play
//if local play dont ask for password and user?
