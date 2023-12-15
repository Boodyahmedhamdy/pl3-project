package datalayer.tables

import datalayer.entites.UserEntity
import slick.jdbc.MySQLProfile.api._
import slick.lifted.{ProvenShape, Tag}

object UsersTable {

  lazy val usersTable = TableQuery[UsersTableTemplate]

}

class UsersTableTemplate(tag: Tag)
  extends Table[UserEntity](tag, "users") {

  def id = column[Int]("user_id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("user_name")
  def roleId = column[Int]("role_id")


  override def * : ProvenShape[UserEntity] =
    (id, name, roleId) <> (UserEntity.tupled, UserEntity.unapply)
}
