package backend.actors.admin

import datalayer.entites.BookEntity

// Admin Responses
sealed trait AdminMessageResponse {
  val responseMessage: String = "response to action"
}
case class DefaultResponse() extends AdminMessageResponse
case class BookCreated(id: String) extends AdminMessageResponse
case class BookUpdated(id: String) extends AdminMessageResponse
case class BookDeleted(book: BookEntity) extends AdminMessageResponse
case class BookNotFound(id: String) extends AdminMessageResponse
case class BooksList(books: List[BookEntity]) extends AdminMessageResponse
case class BooksByName(books: List[BookEntity]) extends AdminMessageResponse
