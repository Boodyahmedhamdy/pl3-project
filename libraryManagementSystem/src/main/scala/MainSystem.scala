import akka.actor.ActorSystem
import backend.actors.admin.{AdminActor, CreateBook, CreateUser, DeleteBook, DeleteUserById, GenerateReportFromEventsLog, GetBookById, GetUserById, ShowAllUsers, ShowBooks, UpdateBook}
import backend.actors.user.{BorrowBook, Login, UserActor}
import slick.dbio.DBIOAction
import utils.data.UserFileHandler

object MainSystem extends App {


  // Example usage
  val system = ActorSystem("LibrarySystem")
  val adminActor = system.actorOf(AdminActor.props(), "admin")
  val userActor = system.actorOf(UserActor.props(), "user")


//  adminActor ! ShowBooks
//  adminActor ! CreateBook("The Hobbit", "J.R.R. Tolkien")
//  adminActor ! ShowBooks
//  adminActor ! DeleteBook(834870.toString)
//  adminActor ! GetBookById(535874)

//  adminActor ! GenerateReportFromEventsLog()

//  adminActor ! ShowAllUsers()
//  adminActor ! CreateUser("boody")
//  adminActor ! DeleteUserById(373519)
//  adminActor ! GetUserById(759439)
//
//  adminActor ! ShowAllUsers()
//
//  userActor ! Login(192808)
//  adminActor ! GenerateReportFromEventsLog()
//  userActor ! ShowBooks

  // ... other interactions with the Admin actor ...
//UserFileHandler.saveCurrentUserId(123)
//println(UserFileHandler.getCurrentUserId)
//UserFileHandler.login(444)
//  println(UserFileHandler.getCurrentUserId)
  userActor ! Login(111)
  system.terminate()

}
