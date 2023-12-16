package backend.actors.user

sealed trait UserMessage {
}

case class BorrowBook(bookId: Int)  extends UserMessage
case class ReturnBook(bookId: Int) extends UserMessage
case object ShowAllBooks extends UserMessage
case class Login(userId: Int) extends UserMessage