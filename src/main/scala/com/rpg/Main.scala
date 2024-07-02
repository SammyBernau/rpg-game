package com.rpg

import com.badlogic.gdx.backends.lwjgl3.{Lwjgl3Application, Lwjgl3ApplicationConfiguration}
import com.rpg.game.RPG

object Main extends App {
    val config = new Lwjgl3ApplicationConfiguration
    config.setTitle("Tavern Adventure")
    config.setWindowedMode(1280, 720)
    config.setForegroundFPS(120)

    new Lwjgl3Application(new RPG, config)
}
