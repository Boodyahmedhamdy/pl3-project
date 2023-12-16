package utils.data

import java.io.{BufferedWriter, File, FileWriter, PrintWriter}
import scala.io.Source

object UserFileHandler {
  val currentUserFile: String = "currentUser.txt"

  def saveCurrentUserId(userId: Int): Unit = {

    val file = new File(currentUserFile)
    if (!file.exists()) {
      file.createNewFile()
      println(s"Created file ${currentUserFile}")
    }
    val writer = new BufferedWriter(new FileWriter(file, false))
    try {
      writer.write(userId.toString)
    } finally {
      writer.close()
    }
  }

  def getCurrentUserId: Int = {
    val source = Source.fromFile(currentUserFile)
    val content = source.getLines().toList
    content.head.toInt
//    Source.fromFile(currentUserFile).getLines().toList.head.toInt
  }



/*    val file = new File(currentUserFile)
    if (!file.exists()) {
      file.createNewFile()
      saveCurrentUserId(0) // If the file is not created yet, create it and set the initial value to 0
    }
    val source = Source.fromFile(file)
    try {
      source.mkString.trim.toInt
    } finally {
      source.close()
    }
  }*/

  def login(userId: Int): Unit = {
    saveCurrentUserId(userId)
  }
}



