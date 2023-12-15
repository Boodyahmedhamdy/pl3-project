package backend.actors.admin

import akka.actor.{Actor, Props}
import backend.actors.EventLogger
import datalayer.entites.BookEntity
import datalayer.repositories.BookRepositoryImpl
import utils.reports.ReportMaker

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
      EventLogger.addEventToHistory("Admin-Create-Book", eventMaker = "Admin")
      sender() ! BookCreated("id")

    case GetBookById(id) =>
      val theBook = bookRepo.getBookById(id)
      println(s"found book with id $id is $theBook")
      EventLogger.addEventToHistory("Admin-Gets-Book", eventMaker = "Admin")
      sender() ! bookRepo.getBookById(id)

    case DeleteBook(id) =>
      bookRepo.deleteBookById(id.toInt)
      println(s"book with id $id was deleted successfully")
      EventLogger.addEventToHistory("Admin-Deletes-Book", eventMaker = "Admin")
      sender() ! DeleteBook(id)

    case ShowBooks =>
      val books = bookRepo.getAllBooks()
      println("------------------------------\nAll Books")
        for (elem <- books) {
          println(s"${elem.id} - ${elem.title} - ${elem.author} - ${elem.borrowedBy}")
        }
      println("-----------------------------")
      BooksList(books).responseMessage
      EventLogger.addEventToHistory("Admin-Show-Books", eventMaker = "Admin")
      sender() ! BooksList(books)

    case GenerateReportFromEventsLog() =>
      ReportMaker.generateReport()
  }


}

object AdminActor {
  def props(): Props = Props[AdminActor]
}


