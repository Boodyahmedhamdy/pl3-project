package utils.reports

import scala.io.Source
import com.github.tototoshi.csv._

object ReportMaker {
  def count(filePath: String): Int = {
    val reader = CSVReader.open(Source.fromFile(filePath))
    val rowCount = reader.all().length
    reader.close()
    rowCount
  }

  def countColumn(filePath: String, columnName: String): Map[String, Int] = {
    val reader = CSVReader.open(Source.fromFile(filePath))
    val rows = reader.all()
    reader.close()

    val columnIndex = rows.head.indexOf(columnName)
    val columnValues = rows.tail.map(_ (columnIndex))
    columnValues.groupBy(identity).view.mapValues(_.size).toMap
  }

  def generateReport(filePath: String = "eventLog.csv",
                     columnNames: List[String] = List("event name", "event maker")): Unit = {

    println(
      """
        |System Events Report
        |===========================
        |""".stripMargin)

    // Get total row count
    val totalEventsCount = count(filePath)
    println(s"Total number of rows in the file: $totalEventsCount")

    val totalBooksCount = count("books.csv")
    println(s"Total number of Books in the System: $totalBooksCount")

    // Get count of unique values in a specific column
    for(columnName <- columnNames) {
      val columnCountMap = countColumn(filePath, columnName)
      println(s"\nCount of unique values in column '$columnName':")
      columnCountMap.foreach { case (value, count) =>
        println(s"'$value': $count")
      }
    }
  }
}
