package com.rpg.game

import com.badlogic.gdx.backends.lwjgl3.{Lwjgl3Application, Lwjgl3ApplicationConfiguration}

object Main extends App:
    val config = new Lwjgl3ApplicationConfiguration
    config.setTitle("RPG")
    config.setWindowedMode(800,400)
    config.setForegroundFPS(60)
    new Lwjgl3Application(new MainGame, config)
