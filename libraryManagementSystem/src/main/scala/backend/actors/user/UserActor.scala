package backend.actors.user

import akka.actor.{Actor, Props}
import backend.actors.EventLogger
import backend.actors.admin.{AdminActor, UserCreated}
import datalayer.repositories.{BookRepositoryImpl, UserRepositoryImpl}
import utils.data.UserFileHandler


class UserActor extends Actor {
  private val bookRepo = BookRepositoryImpl
  private val userRepo = UserRepositoryImpl

  override def preStart(): Unit = {
    // Initialize any resources here (e.g., database connection)
    println("started user actor")
  }

  override def postStop(): Unit = {
    // Close any resources here
    println("closed user Actor")
  }

  override def receive: Receive = {

    case BorrowBook(bookId) =>
      userRepo.userBorrowBook(UserFileHandler.getCurrentUserId, bookId)
      println(s"Book with ID $bookId has been borrowed.")
      EventLogger.addEventToHistory("User-Borrows-Book", eventMaker = "User")


    case ReturnBook(bookId) =>
      userRepo.userReturnBook(UserFileHandler.getCurrentUserId, bookId)
      println(s"Book with ID $bookId has been returned.")
      EventLogger.addEventToHistory("User-Returns-Book", eventMaker = "User")

    case ShowAllBooks =>
      val books = bookRepo.getAllBooks()
      println("------------------------------\nAll Books")
      for (elem <- books) {
        println(s"${elem.id} - ${elem.title} - ${elem.author} - ${elem.borrowedBy}")
      }
      println("-----------------------------")
      EventLogger.addEventToHistory("User-Show-Books", eventMaker = "User")

    case Login(userId: Int) =>
      UserFileHandler.saveCurrentUserId(userId)
      println(s"user with id ${userId} logged in")
      EventLogger.addEventToHistory("User-Login", eventMaker = "User")
      sender() ! UserCreated(userId.toString)
  }
}

object UserActor {
  def props(): Props = Props[UserActor]
}
