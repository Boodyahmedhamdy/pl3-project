package datalayer.repositories

import datalayer.SlickTables
import datalayer.database.DatabaseConnection
import datalayer.entites.BookEntity
import datalayer.tables.BooksTable
import slick.dbio.DBIOAction
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.language.postfixOps
import scala.util.{Failure, Success}

object BookRepositoryImpl extends BookRepository {

  private val booksTable = BooksTable.booksTable
  def showBooks(): Future[Seq[BookEntity]] = {
    var finalResult = List.empty[BookEntity]
    DatabaseConnection.db.run(
      SlickTables.booksTable.result
    )
  }

//  def createBook(title: String, author: String): Unit = {
//    val newBook = BookEntity(
//      id = 0, title = title, author = author, borrowedBy = None
//    )
//    DatabaseConnection.db.run(
//      SlickTables.booksTable += newBook
//    ).onComplete{
//      case Success(value) => println(s"book added with value $value")
//      case Failure(exception) => println(exception)
//    }
//  }

  override def createBook(id: Int = 0, title: String, author: String, borrowerId: Option[Int]): Unit = {
    DatabaseConnection.db.run(
      booksTable += BookEntity(id = id, title = title, author = author, borrowedBy = borrowerId)
    ).onComplete {
      case Success(value) => println(s"added book with name: $title success value is $value")
      case Failure(exception) => println(s"problem while adding $title book problem is $exception")
    }
  }

  override def getAllBooks(): List[BookEntity] = {
    List.empty
  }
}
