package datalayer.database

import com.mysql.cj.jdbc.Driver
import slick.jdbc.JdbcBackend.{Database, _}
//import slick.jdbc.MySQLProfile.api._

object DatabaseConnection {

  val db = Database.forConfig("slick.db")

}
