package com.rpg.game.game

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.maps.objects.{RectangleMapObject, TextureMapObject}
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.math.Rectangle

class OrthogonalTiledMapRendererWithObjects(map: TiledMap) extends OrthogonalTiledMapRenderer(map) {

  private var textureObjects: Map[String, TextureMapObject] = Map()
  override def renderObject(obj: MapObject): Unit = {
    obj match {
      case textureObj: TextureMapObject =>
        addTextureMapObjectObject(textureObj.getName,textureObj)
        batch.draw(textureObj.getTextureRegion, textureObj.getX, textureObj.getY,
          textureObj.getOriginX, textureObj.getOriginY, textureObj.getTextureRegion.getRegionWidth.toFloat, textureObj.getTextureRegion.getRegionHeight.toFloat,
          textureObj.getScaleX, textureObj.getScaleY, textureObj.getRotation)

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

  private def addTextureMapObjectObject(name: String, textureMapObject: TextureMapObject): Unit = {
    textureObjects = textureObjects + (name -> textureMapObject)
  }



  def getTextureMapObject(name: String): TextureMapObject = {
    textureObjects.getOrElse(name, throw new NoSuchElementException("No TextureMapObject found with key " + name))
  }

}

