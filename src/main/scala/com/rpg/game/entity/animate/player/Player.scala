package com.rpg.game.entity.animate.player

import com.rpg.game.entity.Entity
import com.rpg.game.entity.animate.Humanoid

trait Player(pId: Int,
             username: String,
             password: String,
             playerType: PlayerType) extends Humanoid

//check for local or online play
//if local play dont ask for password and user?
