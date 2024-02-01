package com.rpg.game.util

import com.fasterxml.jackson.databind.{JsonNode, ObjectMapper}
import com.fasterxml.jackson.databind.node.ObjectNode
import scala.jdk.CollectionConverters._
import java.io.{File, IOException}
class JsonParser {

  private var counter = 0
  private val mapper = new ObjectMapper()

  def uniqueIdReplacer(file: String): Unit = {
    try {
      val root: JsonNode = mapper.readTree(new File(file))
      val content = root.path("composite").path("content")

      //animations
      val spriteAnimationVOs = content.path("games.rednblack.editor.renderer.data.SpriteAnimationVO")
      spriteAnimationVOs.elements().asScala.foreach(replaceUniqueIds)

      //lights
      val lightVOs = content.path("games.rednblack.editor.renderer.data.LightVO")
      lightVOs.elements().asScala.foreach(replaceUniqueIds)

      //regular images
      val simpleImageVOs = content.path("games.rednblack.editor.renderer.data.SimpleImageVO")
      simpleImageVOs.elements().asScala.foreach(replaceUniqueIds)
      

      val newFileName = getFileNameWithoutExtension(file, ".json")
      val newFile = newFileName + "1" + ".json"
      mapper.writerWithDefaultPrettyPrinter().writeValue(new File(newFile), root)
    } catch {
      case e: IOException => e.printStackTrace()
    }
  }

  private def replaceUniqueIds(simpleImageVO: JsonNode): Unit = {
    if (simpleImageVO.has("uniqueId")) {
      counter += 1
      simpleImageVO.asInstanceOf[ObjectNode].put("uniqueId", counter.toString)
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
