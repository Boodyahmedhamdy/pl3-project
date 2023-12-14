package backend.actors.admin

import datalayer.entites.BookEntity

// Admin Operations
sealed trait AdminMessage {
  val response: AdminMessageResponse = BookNotFound("")
}
case class CreateBook(title: String, author: String) extends AdminMessage
case class UpdateBook(id: String, newTitle: Option[String] = None, newAuthor: Option[String] = None) extends AdminMessage
case class DeleteBook(id: String) extends AdminMessage
case object ShowBooks extends AdminMessage
case class ShowBookByName(title: String) extends AdminMessage
