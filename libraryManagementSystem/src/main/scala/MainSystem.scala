import akka.actor.ActorSystem
import backend.actors.admin.{AdminActor, CreateBook, ShowBooks, UpdateBook}

object MainSystem extends App {


  // Example usage
  val system = ActorSystem("LibrarySystem")
  val adminActor = system.actorOf(AdminActor.props(), "admin")

//  adminActor ! CreateBook("The Hobbit", "J.R.R. Tolkien")
////  adminActor ! UpdateBook("1", newTitle = Some("The Lord of the Rings"))
  adminActor ! ShowBooks

  // ... other interactions with the Admin actor ...

  system.terminate()

}
