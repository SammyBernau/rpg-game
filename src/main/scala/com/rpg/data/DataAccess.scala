package com.rpg.data

trait DataAccess {
  def read(dataSource: String): ReadResult
  def write(dataSource: String, data: Any): Boolean //return success on write?

}




trait ReadResult {
  def getData: Any
  
  def getInt: Int = getData.asInstanceOf[Int]
  def getString: String = getData.asInstanceOf[String]
  
  
}



class JsonReadResult extends ReadResult {

  override def getData: Any = {
  }

  override def getInt: Int = super.getInt

  override def getString: String = super.getString

}


class DatabaseReadResult extends ReadResult {
  override def getData: Any = {}

  override def getInt: Int = super.getInt

  override def getString: String = super.getString
}
