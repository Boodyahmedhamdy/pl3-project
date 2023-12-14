package datalayer.database


import slick.jdbc.MySQLProfile.api._

object DatabaseConnection {

  val db = Database.forConfig("mydb")

}
