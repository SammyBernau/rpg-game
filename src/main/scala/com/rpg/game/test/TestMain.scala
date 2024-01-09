package com.rpg.game.test

import com.badlogic.gdx.backends.lwjgl3.{Lwjgl3Application, Lwjgl3ApplicationConfiguration}
import com.rpg.game.test.MainGame

object TestMain extends App {
    val config = new Lwjgl3ApplicationConfiguration
    config.setTitle("RPG")
    config.setWindowedMode(800, 480)
    config.setForegroundFPS(60)
    new Lwjgl3Application(new MainGame, config)
}
