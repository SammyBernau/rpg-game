package com.rpg.game.game.systems


import com.artemis.ComponentMapper
import com.artemis.annotations.All
import com.artemis.systems.IteratingSystem
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.math.Vector3
import games.rednblack.editor.renderer.components.TransformComponent
import games.rednblack.editor.renderer.components.ViewPortComponent


@All(Array(classOf[ViewPortComponent]))
class CameraSystem extends IteratingSystem {
  private var transformMapper: ComponentMapper[TransformComponent] = _
  private var viewportMapper: ComponentMapper[ViewPortComponent] = _
  private var focus = -1

  override protected def process(entity: Int): Unit = {
    val viewPortComponent = viewportMapper.get(entity)
    val camera = viewPortComponent.viewPort.getCamera
    if (focus != -1) {
      val transformComponent = transformMapper.get(focus)
      if (transformComponent != null) {
        camera.position.set(transformComponent.x, transformComponent.y,0)
      }
    }
  }

  def setFocus(focus: Int): Unit = {
    this.focus = focus
  }
}

