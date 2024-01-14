package com.rpg.game

import com.badlogic.gdx.backends.lwjgl3.{Lwjgl3Application, Lwjgl3ApplicationConfiguration}
import com.rpg.game.RPG
import com.rpg.game.ui.MainMenuScreen

object Main extends App {
    val config = new Lwjgl3ApplicationConfiguration
    config.setTitle("Tavern Adventure")
    config.setWindowedMode(1280, 720)
    config.setForegroundFPS(60)
    new Lwjgl3Application(new RPG, config)
}
