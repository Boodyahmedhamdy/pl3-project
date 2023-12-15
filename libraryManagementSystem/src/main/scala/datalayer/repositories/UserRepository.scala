package datalayer.repositories

import datalayer.entites.UserEntity

trait UserRepository {

  def createUser(id: Int, name: String, roleId: Int)

  def deleteUser(id: Int)

  def getUserByName(name: String): List[UserEntity]

  def getAllUsers(): List[UserEntity]


}
