package com.rpg.game.structure

trait Producer[A] {

  def produce(thing: A): Unit
}
