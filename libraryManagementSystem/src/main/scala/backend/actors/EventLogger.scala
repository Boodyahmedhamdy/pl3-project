package backend.actors

import com.github.tototoshi.csv.CSVWriter

import java.io.{File, FileWriter}
import java.time.LocalDateTime
import scala.annotation.unused


object EventLogger {

  private val logFile = new File("eventLog.csv")

  def addEventToHistory(eventName: String, eventDateTime: LocalDateTime = LocalDateTime.now(), eventMaker: String): Unit = {
    val newEvent = List(eventName, eventDateTime.toString, eventMaker)

    createFileIfNotExists()

    val writer = CSVWriter.open(new FileWriter(logFile, true))
    writer.writeRow(newEvent)
    writer.close()
  }

  private def createFileIfNotExists(): Unit = {
    val fileExists = logFile.exists()

    if (!fileExists) {
      val header = List("event name", "event DateTime", "event maker")
      val writer = CSVWriter.open(new FileWriter(logFile, true))
      writer.writeRow(header)
      writer.close()
    }
  }
}