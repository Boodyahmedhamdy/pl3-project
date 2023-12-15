package backend.actors.admin

import akka.actor.{Actor, Props}
import datalayer.entites.BookEntity
import datalayer.repositories.BookRepositoryImpl

// Define the Admin actor behavior
class AdminActor extends Actor {
  private var books: Map[String, BookEntity] = Map.empty
  private val bookRepo = BookRepositoryImpl

  override def preStart(): Unit = {
    // Initialize any resources here (e.g., database connection)
    println("started admin actor")
  }
  override def postStop(): Unit = {
    // Close any resources here
    println("closed admin Actor")
  }

  def receive: Receive = {
    case CreateBook(title, author) =>


        bookRepo.createBook(title = title, author = author)
//      println(s"create book called with title: $title, author: $author")
      sender() ! BookCreated("id")


    case UpdateBook(id, newTitle, newAuthor) =>
      books.get(id) match {
        case Some(book) =>
          val updatedBook = book.copy(title = newTitle.getOrElse(book.title), author = newAuthor.getOrElse(book.author))
          books += (id -> updatedBook)
          println(books)
          sender() ! BookUpdated(id)
        case None =>
          sender() ! BookNotFound(id)
      }


//    case DeleteBook(id) =>
//      books.remove(id) match {
//        case Some(book) =>
////          sender() ! BookDeleted(book)
//        case None =>
//          sender() ! BookNotFound(id)
//      }


    case ShowBooks =>
      val result =
        bookRepo.showBooks().value

      sender() ! BooksList(books.values.toList)


    case ShowBookByName(title) =>
      val matchingBooks = books.values.filter(_.title == title)
      sender() ! BooksByName(matchingBooks.toList)
    case _ => // Handle any unexpected messages
      // log.warning("Unhandled message: {}", message)
  }

  private def generateId(): String = ""// Implement your ID generation logic here
}

object AdminActor {
  def props(): Props = Props[AdminActor]
}


