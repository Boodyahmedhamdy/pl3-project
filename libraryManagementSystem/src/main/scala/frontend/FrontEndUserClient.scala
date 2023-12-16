package frontend


object FrontEndUserClient {

  def printUserTasks(
                    onShowAllBooksSelected:Unit,
                    onBorrowBookSelected:  Unit,
                    onReturnBookSelected:  Unit
                    ): Unit = {
    // Display available tasks
    println("Available tasks:")
    println("1. Show All Books")
    println("2. Borrow Book")
    println("3. Return Book")

    // Get user input
    val input = scala.io.StdIn.readLine()

    // Validate input and execute task
    try {
      val taskNumber = input.toInt
      if (taskNumber >= 1 && taskNumber <= 3) {
        performUserTask(
          taskNumber,
          onShowAllBooksSelected: Unit,
          onBorrowBookSelected: Unit,
          onReturnBookSelected: Unit
        )
      } else {
        throw new IllegalArgumentException("Invalid task number. Please enter a number between 1 and 3.")
      }
    } catch {
      case e: NumberFormatException =>
        println("Invalid input. Please enter a valid number.")
        printUserTasks(
          onShowAllBooksSelected: Unit,
          onBorrowBookSelected: Unit,
          onReturnBookSelected: Unit
        ) // Recursively call if input is not a number
    }
  }

  def performUserTask(taskNumber: Int,
                      onShowAllBooksSelected: Unit,
                      onBorrowBookSelected: Unit,
                      onReturnBookSelected: Unit): Unit = {
    // Implement specific logic for each task based on taskNumber
    taskNumber match {
      case 1 => onShowAllBooksSelected
      case 2 => onBorrowBookSelected
      case 3 => onReturnBookSelected
      case _ => println("Invalid task selection. Please choose again.")
    }
  }

  def getBookId(): Int = {
    println("Enter Book Id: ")
    scala.io.StdIn.readLine().toInt
  }


}
