package com.rpg.game.util

import com.fasterxml.jackson.databind.{JsonNode, ObjectMapper}
import com.fasterxml.jackson.databind.node.ObjectNode
import scala.jdk.CollectionConverters._
import java.io.{File, IOException}
class JsonParser {

  private var imageCounter = 0
  private var animationCounter: Int = _
  private val mapper = new ObjectMapper()

  def uniqueIdReplacer(file: String): Unit = {
    try {
      val root: JsonNode = mapper.readTree(new File(file))
      val content = root.path("composite").path("content")
      val simpleImageVOs = content.path("games.rednblack.editor.renderer.data.SimpleImageVO")
      simpleImageVOs.elements().asScala.foreach(replaceImageUniqueIds)
      animationCounter = imageCounter + 1
      
      val spriteAnimationVOs = content.path("games.rednblack.editor.renderer.data.SpriteAnimationVO")
      spriteAnimationVOs.elements().asScala.foreach(replaceAnimationUniqueIds)
      val newFileName = getFileNameWithoutExtension(file, ".json")
      val newFile = newFileName + "1" + ".json"
      mapper.writerWithDefaultPrettyPrinter().writeValue(new File(newFile), root)
    } catch {
      case e: IOException => e.printStackTrace()
    }
  }

  private def replaceImageUniqueIds(simpleImageVO: JsonNode): Unit = {
    if (simpleImageVO.has("uniqueId")) {
      imageCounter += 1
      simpleImageVO.asInstanceOf[ObjectNode].put("uniqueId", imageCounter.toString)
    }
  }

  private def replaceAnimationUniqueIds(simpleImageVO: JsonNode): Unit = {
    if (simpleImageVO.has("uniqueId")) {
      animationCounter += 1
      simpleImageVO.asInstanceOf[ObjectNode].put("uniqueId", animationCounter.toString)
    }
  }

  private def getFileNameWithoutExtension(file: String, extension: String): String = {
    if (file.endsWith(extension)) {
      file.substring(0, file.lastIndexOf(extension))
    } else {
      file
    }
  }


}
