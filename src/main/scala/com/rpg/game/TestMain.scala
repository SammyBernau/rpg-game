package com.rpg.game

import com.rpg.game.entity.animate.Animal

object TestMain {
  def main(args: Array[String]): Unit = {
    var animal = new Animal("cow", 12, 1,1,1,1,1,1,1,1,1,1,1,1,1,1,false)
    animal.setAge(10)
    animal.setWalking(true)
    println(animal.getWalkingState)
    println(animal.getAge)
    animal.makeAnimalSound()
  }

}
