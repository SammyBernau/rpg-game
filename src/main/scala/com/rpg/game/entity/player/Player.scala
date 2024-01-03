package com.rpg.game.entity.player

import com.rpg.game.entity.Entity

trait Player(id: Int,
             username: String,
             password: String,
             playerType: PlayerType) extends Entity

//check for local or online play
//if local play dont ask for password and user?
