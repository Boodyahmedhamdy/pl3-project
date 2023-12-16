import akka.actor.ActorSystem
import backend.actors.admin.{AdminActor, CreateBook, CreateUser, DeleteBook, DeleteUserById, GenerateReportFromEventsLog, GetBookById, GetUserById, ShowAllUsers, ShowBooks, UpdateBook}
import backend.actors.user.{BorrowBook, Login, ReturnBook, UserActor}
import frontend.{FrontEndClient, FrontEndUserClient}
import slick.dbio.DBIOAction
import utils.data.UserFileHandler

object MainSystem extends App {


  // Example usage
  val system = ActorSystem("LibrarySystem")
  val adminActor = system.actorOf(AdminActor.props(), "admin")
  val userActor = system.actorOf(UserActor.props(), "user")

/*  val selectedRole = FrontEndClient.chooseRole
  if(selectedRole == "admin") {

  } else {
    FrontEndUserClient.printUserTasks(
      onShowAllBooksSelected = {
        userActor ! ShowBooks
      },
      onBorrowBookSelected = {
        userActor ! BorrowBook(
          FrontEndUserClient.getBookId()
        )
      },
      onReturnBookSelected = {
        userActor ! ReturnBook(
          FrontEndUserClient.getBookId()
        )
      }
    )
  }*/



//  adminActor ! ShowBooks
//  adminActor ! CreateBook("nothing", "J.R.R. Tolkien")
//  adminActor ! ShowBooks
//  adminActor ! DeleteBook(932021.toString)
//  adminActor ! GetBookById(535874)

//  adminActor ! GenerateReportFromEventsLog()

//  adminActor ! ShowAllUsers()
//  adminActor ! CreateUser("boody")
//  adminActor ! DeleteUserById(373519)
//  adminActor ! GetUserById(759439)
//
//  adminActor ! ShowAllUsers()
//
//  userActor ! Login(222)
//  adminActor ! GenerateReportFromEventsLog()
//  userActor ! ShowBooks

  // ... other interactions with the Admin actor ...
//UserFileHandler.saveCurrentUserId(123)
//println(UserFileHandler.getCurrentUserId)
//UserFileHandler.login(444)
//  println(UserFileHandler.getCurrentUserId)
//  userActor ! Login(111)
  system.terminate()

}
