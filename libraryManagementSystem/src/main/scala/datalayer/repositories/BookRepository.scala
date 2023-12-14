package datalayer.repositories

import datalayer.SlickTables
import datalayer.database.DatabaseConnection
import datalayer.entites.BookEntity
import slick.dbio.DBIOAction
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

object BookRepository {
  def showBooks(): Future[Seq[BookEntity]] = {
    var finalResult = List.empty[BookEntity]
    DatabaseConnection.db.run(
      SlickTables.booksTable.result
    )
  }

  def createBook(title: String, author: String): Unit = {
    val newBook = BookEntity(
      id = 0, title = title, author = author, borrowedBy = None
    )
    DatabaseConnection.db.run(
      SlickTables.booksTable += newBook
    ).onComplete{
      case Success(value) => println(s"book added with value $value")
      case Failure(exception) => println(exception)
    }
  }


}
