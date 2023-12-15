import akka.actor.ActorSystem
import backend.actors.admin.{AdminActor, CreateBook, DeleteBook, GenerateReportFromEventsLog, GetBookById, ShowBooks, UpdateBook}
import slick.dbio.DBIOAction

object MainSystem extends App {


  // Example usage
  val system = ActorSystem("LibrarySystem")
  val adminActor = system.actorOf(AdminActor.props(), "admin")

//  adminActor ! ShowBooks
//  adminActor ! CreateBook("The Hobbit", "J.R.R. Tolkien")
//  adminActor ! ShowBooks
//  adminActor ! DeleteBook(834870.toString)
//  adminActor ! GetBookById(535874)

  adminActor ! GenerateReportFromEventsLog()
  // ... other interactions with the Admin actor ...



  system.terminate()

}
