import utils.data.Book

object Main {
  def main(args: Array[String]): Unit = {

    // Prompt user for book name
    print("Enter book name: ")
    val name = scala.io.StdIn.readLine()

    // Prompt user for author name
    print("Enter book author: ")
    val author = scala.io.StdIn.readLine()

    // Create a Book instance with user data
    val book = Book(name, author)

    // Validate user input
    val validationErrors = validateBookData(book)

    // If validation errors exist, inform user and request re-entry
    if (validationErrors.nonEmpty) {
      println("Invalid data:")
      validationErrors.foreach { error => println(s" - $error") }
      println("Please re-enter book data.")
      main(args)
    } else {
      // Inform user about successful book creation
      println("Book created successfully!")
      println(s"Book details: $book")
    }
  }

  // Function to validate book data
  def validateBookData(book: Book): List[String] = {
    val errors = List.newBuilder[String]

    if (book.name.trim.isEmpty) {
      errors += "Book name cannot be empty."
    }

    if (book.author.trim.isEmpty) {
      errors += "Author name cannot be empty."
    }
    errors.result()
  }
}
