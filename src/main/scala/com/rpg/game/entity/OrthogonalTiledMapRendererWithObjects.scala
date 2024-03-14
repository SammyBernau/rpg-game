package com.rpg.game.entity



import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.maps.objects.{RectangleMapObject, TextureMapObject}
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Rectangle

class OrthogonalTiledMapRendererWithObjects(map: TiledMap) extends OrthogonalTiledMapRenderer(map) {

  override def renderObject(obj: MapObject): Unit = {
    obj match {
      case textureObj: TextureMapObject =>
        batch.draw(textureObj.getTextureRegion, textureObj.getX, textureObj.getY,
          textureObj.getOriginX, textureObj.getOriginY, textureObj.getTextureRegion.getRegionWidth, textureObj.getTextureRegion.getRegionHeight,
          textureObj.getScaleX, textureObj.getScaleY, textureObj.getRotation)
        if (textureObj.getProperties.containsKey("this")) println(textureObj.getRotation)

      case rectObject: RectangleMapObject =>
        val rect: Rectangle = rectObject.getRectangle
        val sr = new ShapeRenderer()
        sr.setProjectionMatrix(batch.getProjectionMatrix)
        sr.begin(ShapeRenderer.ShapeType.Line)
        sr.rect(rect.x, rect.y, rect.width, rect.height)
        sr.end()

      case _ =>
    }
  }
}

