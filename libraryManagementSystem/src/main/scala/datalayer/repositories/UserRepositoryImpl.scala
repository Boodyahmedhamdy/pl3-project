package datalayer.repositories
import datalayer.entites.UserEntity

import java.io.File
import scala.io.Source


object UserRepositoryImpl extends UserRepository {

  override def createUser(id: Int, name: String, roleId: Int): Unit = {

  }

  override def deleteUser(id: Int): Unit = {

  }

  override def getUserByName(name: String): List[UserEntity] = {
    List.empty
  }

  override def getAllUsers(): List[UserEntity] = {
    List.empty
  }
}
