package backend.actors.admin

import akka.actor.{Actor, Props}
import backend.actors.EventLogger
import datalayer.repositories.{BookRepositoryImpl, UserRepositoryImpl}
import utils.reports.ReportMaker

// Define the Admin actor behavior
class AdminActor extends Actor {
  private val bookRepo = BookRepositoryImpl
  private val userRepo = UserRepositoryImpl

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

    case CreateUser(userName: String) =>
      userRepo.createUser(name = userName)
      EventLogger.addEventToHistory("Admin-Creates-User", eventMaker = "Admin")
      println(s"Created new User with name $userName")
      sender() ! UserCreated(userName: String)

    case DeleteUserById(userId: Int) =>
      userRepo.deleteUser(userId)
      EventLogger.addEventToHistory("Admin-Deletes-User", eventMaker = "Admin")
      println(s"Deleted User with id ${userId}")
      sender() ! UserDeleted(userId)

    case ShowAllUsers() =>
      val users = userRepo.getAllUsers()
      println(
        """
          |========================
          |     All Users
          |========================
          |""".stripMargin)
      users.foreach(user => {
        println(s"${user.id} - ${user.name} - ${user.role} - ${user.bookIds}")
      })
      println("==============================")
      EventLogger.addEventToHistory("Admin-Gets-All-Users", eventMaker = "Admin")

    case GetUserById(userId) =>
      val user = userRepo.getUserById(userId)
      println(s"you got user ${user.name} with id ${user.id}")
      EventLogger.addEventToHistory("Admin-Gets-User", eventMaker = "Admin")


  }


}

object AdminActor {
  def props(): Props = Props[AdminActor]
}


