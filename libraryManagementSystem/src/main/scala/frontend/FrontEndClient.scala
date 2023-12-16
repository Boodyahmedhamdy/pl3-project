package frontend

import backend.actors.user.UserActor

object FrontEndClient {

  def chooseRole: String = {
    // Display available roles
    println("Please choose your role:")
    println("1. Admin")
    println("2. User")

    // Get user input
    val input = scala.io.StdIn.readLine()

    // Validate input
    val validInput = input match {
      case "1" => "admin"
      case "2" => "user"
      case _ => {
        println("Invalid input. Please enter 1 for admin or 2 for user.")
        chooseRole // Recursively call the function if input is invalid
      }
    }

    validInput
  }

}
