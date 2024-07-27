package com.rpg.entity.item.projectiles.projectile_systems

import com.badlogic.gdx.maps.objects.TextureMapObject
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.physics.box2d.Body
import com.rpg.game.structure.Consumer
import com.rpg.game.systems.rendering.{RenderListener, RenderSystem}
import com.rpg.game.systems.rendering.services.gameobjects.{GameObject, GameObjectCache}

import javax.inject.{Inject, Singleton}

@Singleton
class ProjectileMoveConsumer @Inject(val renderSystem: RenderSystem, gameObjectCache: GameObjectCache, projectileMoveService: ProjectileMoveCache) extends Consumer with RenderListener {
  

  override def renderListener(): Unit = {
    consume()
  }
  
  private def move(body: Body,speed: Float, angle: Float): Unit = synchronized {

    //Stops ball from slowing down over time (constant speed)
    body.setLinearDamping(0f)

    //Calculate the force to be applied on the fireball
    val forceX = MathUtils.cos(angle) * speed
    val forceY = MathUtils.sin(angle) * speed

    //Apply the force to the fireball
    body.setLinearVelocity(forceX, forceY)
  }

  override def consume(): Unit = synchronized {
    if (projectileMoveService.getCache.nonEmpty) {
      projectileMoveService.getCache.foreach { projectileMoveRequest =>
        val name = projectileMoveRequest.projectileName
        val speed = projectileMoveRequest.speed
        val angle = projectileMoveRequest.angle

        val gameObject = gameObjectCache.get(name).get
        val fixture = gameObject.fixture
        val body = fixture.getBody
        val textureMapObject = gameObject.mapObject.asInstanceOf[TextureMapObject]


        textureMapObject.setRotation((textureMapObject.getRotation * MathUtils.radiansToDegrees) - 90)

        
        //set angle rotation of projectile body
        body.setTransform(body.getPosition, angle - (MathUtils.degreesToRadians * 90))

        //move projectile
        move(body, speed, angle)
        projectileMoveService.remove(projectileMoveRequest)
      }
    }
  }
}
