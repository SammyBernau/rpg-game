package com.rpg.entity

import com.badlogic.gdx.physics.box2d.{Body, BodyDef, PolygonShape}
import com.rpg.entity.animate.player.Player
import com.rpg.game.systems.physics.World.WORLD


class EntityObject() {

    def this(player: Player) = {
        this()
        val playerBodyDef = new BodyDef()
        playerBodyDef.`type` = BodyDef.BodyType.DynamicBody
        playerBodyDef.position.set(player.playerSettings.x,player.playerSettings.y)
        playerBodyDef.linearDamping = 1f

        val playerBody = WORLD.createBody(playerBodyDef)
        
        val playerShape = new PolygonShape()



    }

}
