package datalayer.repositories
import com.github.tototoshi.csv.{CSVReader, CSVWriter}
import datalayer.entites.{BookEntity, UserEntity}
import utils.data.Utils

import java.io.File
import scala.io.Source


object UserRepositoryImpl extends UserRepository {

  private val csvFile = new File("users.csv")

  private def createFileIfNotExists(): Unit = {
    val fileExists = csvFile.exists()

    if (!fileExists) {
      csvFile.createNewFile()
      val header = Seq("id", "user_name", "user_role", "bookIds")
      val writer = CSVWriter.open(csvFile)
      writer.writeRow(header)
      writer.close()
    }
  }


  override def getUserById(id: Int): UserEntity = {
    val allUsers = getAllUsers()
    val filteredBooks = allUsers.filter(_.id == id)

    filteredBooks.head
  }

  override def getUserBorrowedBooksById(id: Int): List[BookEntity] = {
    ???
  }

  override def userBorrowBook(userId: Int, bookId: Int): Unit = {
    val allUsers = getAllUsers()
    val updatedUsers = allUsers.map { user =>
      if (user.id == userId) {
        val updatedBookIds = bookId :: user.bookIds
        UserEntity(user.id, user.name, user.role, updatedBookIds)
      } else {
        user
      }
    }
  }
    def userReturnBook(userId: Int, bookId: Int): Unit = {
      val user = getUserById(userId)
      user.bookIds = user.bookIds.appendedAll(List(bookId))
      deleteUser(userId)
      createUser(userId, user.name, bookIds = user.bookIds)
  }

    def createUser(id: Int, name: String, role: String, bookIds: List[Int]): Unit = {
    val writer = CSVWriter.open(csvFile, append = true)

    val row = Seq(id.toString, name, role, bookIds)
    writer.writeRow(row)

    writer.close()
  }

  override def deleteUser(id: Int): Unit = {
    val reader = CSVReader.open(csvFile)
    val allLines: List[List[String]] = reader.all()
    reader.close()

    val updatedLines = allLines.filterNot { line =>
      line.headOption.exists(_.toInt == id)
    }

    val writer = CSVWriter.open(csvFile)
    writer.writeAll(updatedLines)
    writer.close()
  }

  override def getUserByName(name: String): List[UserEntity] = {
    val reader = CSVReader.open(csvFile)
    val lines: List[List[String]] = reader.all()
    reader.close()

    // Parse lines into User objects that match the provided name
    val usersWithName = lines.tail.flatMap { line =>
      val id = line.head.toInt
      val userName = line(1)
      val role = line(2)
      val bookIds = if (line.length > 3) line(3).split(",").map(_.toInt).toList else List.empty[Int]

      if (userName.toLowerCase.contains(name.toLowerCase)) {
        Some(UserEntity(id, userName, role, bookIds))
      } else {
        None
      }
    }
    usersWithName
  }

  override def getAllUsers(): List[UserEntity] = {
    val reader = CSVReader.open(csvFile)
    val lines: List[List[String]] = reader.all()
    reader.close()

    // Parse lines into User objects
    lines.tail.map { line =>
      val id = line.head.toInt
      val name = line(1)
      val role = line(2)
      val bookIds = List.empty
      UserEntity(id, name, role, bookIds)
    }
  }
}
