package com.rpg.game

import com.rpg.game.entity.animate.Animal

object TestMain {
  def main(args: Array[String]): Unit = {
    var animal = new Animal("cow", 12, 1,1,1,1,1,1,1,1,1,1,1,1,1,1,false)
    animal.makeAnimalSound()
  }

}
