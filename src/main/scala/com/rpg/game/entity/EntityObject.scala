package com.rpg.game.entity;

import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.rpg.game.entity.animate.player.Player
import com.rpg.game.game.config.GameConfig.GameWorld
import com.rpg.game.game.config.GameConfig.GameWorld.WORLD;


class EntityObject() {

    def this(player: Player) = {
        this()
        val playerBodyDef = new BodyDef()
        playerBodyDef.`type` = BodyDef.BodyType.DynamicBody
        playerBodyDef.position.set(player.playerSettings.x,player.playerSettings.y)
        playerBodyDef.linearDamping = 1f

        val playerBody = WORLD.createBody(playerBodyDef)



    }

}
