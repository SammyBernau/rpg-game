package com.rpg.entity.item.projectiles

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.maps.objects.TextureMapObject
import com.badlogic.gdx.physics.box2d.Fixture

trait Projectile[A] {
  
  private var projectiles: List[A] = List()
  
  def addProjectile(projectile: A): Unit = synchronized {
    projectiles = projectile :: projectiles
  }
  
  def removeProjectile(projectile: A): Unit = synchronized {
    projectiles = projectiles.filterNot(_ == projectile)
  }
  def getProjectiles: List[A] = projectiles
  def create(): Unit = {}
}
