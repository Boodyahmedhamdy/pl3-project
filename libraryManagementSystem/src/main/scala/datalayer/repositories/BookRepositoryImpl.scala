package datalayer.repositories

import com.github.tototoshi.csv.{CSVReader, CSVWriter}
import datalayer.entites.BookEntity

import java.io.File
import scala.language.postfixOps
import utils.data.Utils

object BookRepositoryImpl extends BookRepository {

  private val csvFile = new File("books.csv")

  private def createFileIfNotExists(): Unit = {
    val fileExists = csvFile.exists()

    if (!fileExists) {
      csvFile.createNewFile()
      val header = Seq("id", "book_name", "book_author", "borrowed_by")
      val writer = CSVWriter.open(csvFile)
      writer.writeRow(header)
      writer.close()
    }
  }

  override def createBook(id: Int = Utils.generateId() , title: String, author: String, borrowerId: Int = -1): Unit = {

    createFileIfNotExists()

    val writer = CSVWriter.open(csvFile, append = true)

    val row = Seq(id, title, author, borrowerId)
    writer.writeRow(row)

    writer.close()
  }

  override def getAllBooks(): List[BookEntity] = {

    createFileIfNotExists()

    val reader = CSVReader.open(csvFile)
    val lines = reader.all()
    reader.close()

    lines.tail.map { fields =>
      val id = fields(0).toInt
      val name = fields(1)
      val author = fields(2)
      val borrowedBy = fields(3)
      BookEntity(id, name, author, borrowedBy.toInt)
    }
  }

  override def deleteBookById(bookId: Int): Unit = {

    val allBooks = getAllBooks()
    val filteredBooks = allBooks.filterNot(_.id == bookId)

    // write the file again without selected row
    val writer = CSVWriter.open(csvFile)
    writer.writeRow(Seq("id", "book_name", "book_author", "borrowed_by"))
    filteredBooks.foreach { book =>
      writer.writeRow(Seq(book.id.toString, book.title, book.author, book.borrowedBy))
    }
    writer.close()
  }

  override def getBookById(id: Int): BookEntity = {
    val allBooks = getAllBooks()
    val filteredBooks = allBooks.filter(_.id == id)

    filteredBooks.head
  }
}
