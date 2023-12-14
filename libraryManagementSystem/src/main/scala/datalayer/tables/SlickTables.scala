package datalayer

import datalayer.entites.BookEntity
import slick.lifted.ProvenShape


object SlickTables {
  import slick.jdbc.MySQLProfile.api._

  class BookTable(tag: Tag) extends Table[BookEntity](tag, "books") {
    def bookId = column[Int]("book_id", O.PrimaryKey, O.AutoInc, O.Unique)
    def bookTitle = column[String]("book_title")
    def bookAuthor = column[String]("book_author")
    def bookBorrowedById = column[Option[Int]]("borrowed_by")

    override def * : ProvenShape[BookEntity] =
      (bookId, bookTitle, bookAuthor, bookBorrowedById) <> (BookEntity.tupled, BookEntity.unapply)

  }

    lazy val booksTable = TableQuery[BookTable]

}
