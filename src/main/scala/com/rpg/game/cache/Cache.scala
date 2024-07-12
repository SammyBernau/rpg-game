package com.rpg.game.cache

trait Cache[Input] {
  
  def add(element: Input): Unit
  def remove(element: Input): Unit

}
