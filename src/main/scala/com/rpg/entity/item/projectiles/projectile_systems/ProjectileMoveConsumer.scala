package com.rpg.entity.item.projectiles.projectile_systems

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.maps.objects.TextureMapObject
import com.badlogic.gdx.math.{MathUtils, Vector2}
import com.badlogic.gdx.physics.box2d.{Body, MassData}
import com.rpg.entity.item.projectiles.projectile_systems.ghostfireball.GhostFireballAnimation
import com.rpg.game.structure.Consumer
import com.rpg.game.systems.concurrent.Scheduler
import com.rpg.game.systems.event.tick.{SubTickEvent, SubTickSystem}
import com.rpg.game.systems.rendering.gameobjects.{GameObject, GameObjectCache}
import com.rpg.game.systems.rendering.{RenderEvent, RenderSystem}

import javax.inject.{Inject, Singleton}

@Singleton
class ProjectileMoveConsumer @Inject(val subTickSystem: SubTickSystem,
                                     gameObjectCache: GameObjectCache,
                                     projectileMoveCache: ProjectileMoveCache,
                                     scheduler: Scheduler) extends Consumer with SubTickEvent {


  override def tick(tick: Long): Unit = {
    consume()
  }

  private def move(body: Body,
                   speed: Float,
                   angle: Float): Unit = synchronized {

    //Stops ball from slowing down over time (constant speed)
    body.setLinearDamping(0f)
    //Calculate the force to be applied on the fireball
    val forceX = MathUtils.cos(angle) * speed
    val forceY = MathUtils.sin(angle) * speed
    println(s"forceX: ${forceX}")
    println(s"forceY: ${forceY}")

    //Apply the force to the fireball
    println(s"Body Mass: ${body.getMass}")
    body.setLinearVelocity(forceX, forceY)
    
    
  }

  override def consume(): Unit = {
      projectileMoveCache.getCache.foreach { projectileMoveRequest =>
        val name = projectileMoveRequest.projectileName
        val speed = projectileMoveRequest.speed
        val angle = projectileMoveRequest.angle

        gameObjectCache
          .get(name)
          .fold{
            println(s"Unable to complete move request for fireball with name: ${name}")
          }
          { gameObject =>

            val fixture = gameObject.fixture
            val body = fixture.getBody
            val textureMapObject = gameObject.mapObject.asInstanceOf[TextureMapObject]

            val massData = new MassData()
            massData.mass = 1f
            body.setMassData(massData)

            //set angle rotation of projectile body
            body.setTransform(body.getPosition, angle - (MathUtils.degreesToRadians * 90))

            //move projectile
            move(body, speed, angle)
            //Remove projectile move request
            projectileMoveCache.remove(projectileMoveRequest)
          }
      }
  }
}
