package datalayer.repositories

import datalayer.entites.BookEntity

trait BookRepository {
  def createBook(title: String, author: String)


}
