package datalayer.tables

import datalayer.entites.BookEntity
import slick.jdbc.MySQLProfile.api._
import slick.lifted.ProvenShape

object BooksTable {

  lazy val booksTable = TableQuery[BooksTableTemplate]

}

class BooksTableTemplate(tag: Tag)
  extends Table[BookEntity](tag, "books") {

  def id = column[Int]("book_id", O.PrimaryKey, O.AutoInc)
  def title = column[String]("book_title")
  def author = column[String]("book_author")
  def borrowerBy = column[Option[Int]]("borrowed_by", O.Default(None))

  override def * : ProvenShape[BookEntity] =
    (id, title, author, borrowerBy) <> (BookEntity.tupled, BookEntity.unapply)
}
