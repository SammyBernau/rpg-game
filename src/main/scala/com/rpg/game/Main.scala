package com.rpg.game

import com.badlogic.gdx.backends.lwjgl3.{Lwjgl3Application, Lwjgl3ApplicationConfiguration}
import com.rpg.game.RPG

object Main extends App {
    val config = new Lwjgl3ApplicationConfiguration
    config.setTitle("RPG")
    config.setWindowedMode(800, 480)
    config.setForegroundFPS(60)
    new Lwjgl3Application(new RPG, config)
}
