package com.rpg.game

import com.rpg.game.util.JsonParser


object MainTemp {

  def main(args: Array[String]): Unit = {
    val fileParser = new JsonParser
    val file = "assets/MainScene.json"
    fileParser.uniqueIdReplacer(file)

  }

}
