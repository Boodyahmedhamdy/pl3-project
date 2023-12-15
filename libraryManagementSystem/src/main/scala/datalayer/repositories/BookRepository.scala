package datalayer.repositories

import datalayer.entites.BookEntity

trait BookRepository {

  def createBook(id: Int, title: String, author: String, borrowerId: Int)

  def getAllBooks(): List[BookEntity]

  def deleteBookById(bookId: Int)

  def getBookById(id: Int): BookEntity



}
