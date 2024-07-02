package com.rpg.entity.animate.player

/**
 * everything included in the Set is that object
 *
 * For Example: 
 * every Owner is an Owner
 * every Owner is an Admin and every Admin is an Admin etc. */
sealed trait PlayerType {
  val includes: Set[PlayerType]
}

/**
 * Owner of game aka. Sam Bernau
 * */
case object Owner extends PlayerType {
  final val includes: Set[PlayerType] = Set(Owner)
}

case object Admin extends PlayerType {
  final val includes: Set[PlayerType] = Set(Owner, Admin)
}

case object GameManager extends PlayerType {
  final val includes: Set[PlayerType] = Set(Owner, Admin, GameManager)
}

case object BaseUser extends PlayerType {
  final val includes: Set[PlayerType] = Set(Owner, Admin, GameManager, BaseUser)
}

 
