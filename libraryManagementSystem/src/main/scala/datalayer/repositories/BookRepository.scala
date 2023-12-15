package datalayer.repositories

import datalayer.entites.BookEntity

trait BookRepository {

  def createBook(id: Int, title: String, author: String, borrowerId: Option[Int] = None)

  def getAllBooks(): List[BookEntity]



}
