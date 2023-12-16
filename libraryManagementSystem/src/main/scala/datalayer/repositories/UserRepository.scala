package datalayer.repositories

import datalayer.entites.{BookEntity, UserEntity}
import utils.data.Utils

trait UserRepository {

  def createUser(id: Int = Utils.generateId(),
                 name: String, role: String = "User",
                 bookIds: List[Int] = List.empty)

  def deleteUser(id: Int)

  def getUserByName(name: String): List[UserEntity]

  def getUserById(id: Int): UserEntity

  def userBorrowBook(userId: Int, bookId: Int)

  def userReturnBook(userId: Int, bookId: Int)

  def getAllUsers(): List[UserEntity]

}
