package utils.data

case class Book(name: String,
                author: String,
                var borrowerId: Option[Int] = None)

