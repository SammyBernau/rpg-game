package com.rpg.game.entity.animate.player.hyperlapremnantsDELETE_WHEN_NOT_NEEDED_FOR_NOTES

import com.artemis.ComponentMapper
import com.artemis.annotations.All
import com.artemis.systems.IteratingSystem
import com.badlogic.gdx.graphics.g2d.Animation
import games.rednblack.editor.renderer.components.physics.PhysicsBodyComponent
import games.rednblack.editor.renderer.components.sprite.{SpriteAnimationComponent, SpriteAnimationStateComponent}
import games.rednblack.editor.renderer.components.{ParentNodeComponent, TransformComponent}


@All(Array(classOf[PlayerComponent]))
class PlayerAnimation extends IteratingSystem{

  private var parentMapper: ComponentMapper[ParentNodeComponent] = _
  private var physicsMapper: ComponentMapper[PhysicsBodyComponent] = _
  private var spriteMapper: ComponentMapper[SpriteAnimationComponent] = _
  private var spriteStateMapper: ComponentMapper[SpriteAnimationStateComponent] = _
  private var transformMapper: ComponentMapper[TransformComponent] = _
  private var lastDirection = "front"


  override def process(entityId: Int): Unit = {
    val parentNodeComponent = parentMapper.get(entityId)
    val body = physicsMapper.get(parentNodeComponent.parentEntity).body

    if(body == null)
      return

    val spriteAnimationComponent = spriteMapper.get(entityId)
    val spriteAnimationStateComponent = spriteStateMapper.get(entityId)
    val transformComponent = transformMapper.get(entityId)

    if (!body.getLinearVelocity.isZero(0.1f)) {
      if (body.getLinearVelocity.x < 0) {
        spriteAnimationComponent.playMode = Animation.PlayMode.LOOP
        spriteAnimationComponent.currentAnimation = "left"
        lastDirection = "left"
      } else if (body.getLinearVelocity.x > 0) {
        spriteAnimationComponent.playMode = Animation.PlayMode.LOOP
        spriteAnimationComponent.currentAnimation = "right"
        lastDirection = "right"
      } else if (body.getLinearVelocity.y < 0) {
        spriteAnimationComponent.playMode = Animation.PlayMode.LOOP
        spriteAnimationComponent.currentAnimation = "front"
        lastDirection = "front"
      } else if (body.getLinearVelocity.y > 0.016666668) {
        spriteAnimationComponent.playMode = Animation.PlayMode.LOOP
        spriteAnimationComponent.currentAnimation = "back"
        lastDirection = "back"
      }
      spriteAnimationStateComponent.set(spriteAnimationComponent)
    } else {
      lastDirection match {
        case "left" => spriteAnimationComponent.currentAnimation = "left_static"
        case "right" => spriteAnimationComponent.currentAnimation = "right_static"
        case "front" => spriteAnimationComponent.currentAnimation = "front_static"
        case "back" => spriteAnimationComponent.currentAnimation = "back_static"
      }
      spriteAnimationStateComponent.set(spriteAnimationComponent)
    }


  }

}
