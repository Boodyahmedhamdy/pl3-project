package Project
import Project.Bank.Person.LiveTheLife
import akka.actor.{Actor, ActorSystem, Props}
import scala.collection.mutable.ListBuffer

object Bank extends App {

  object Person{
    case class LiveTheLife()
  }
  class Person extends Actor {
    import Project.Bank.BankAccount.{Deposite, GetStatement, Withdraw}

    override def receive:Receive = {
      case LiveTheLife => {
        bankAccount ! Deposite(500)
        bankAccount ! Deposite(500)
        bankAccount ! Withdraw(500)
        bankAccount ! GetStatement()
      }
    }
  }

  object BankAccount{
    trait Operation
    case class Deposite(money: BigDecimal) extends Operation
    case class Withdraw(money: BigDecimal) extends Operation
    case class GetStatement()
    case class StatementRecord(op: Operation, balance:BigDecimal)
  }

  class BankAccount extends Actor {

    import Project.Bank.BankAccount.{Deposite, StatementRecord, Withdraw}

    var balance:BigDecimal = 0
    val statement = ListBuffer[StatementRecord]()

    import BankAccount._

    override def receive: Receive = {
      case Deposite(v) => {
        println(s"${context.self} got deposit ${v}")
        balance += v
        statement += StatementRecord(Deposite(v), balance)
      }
      case Withdraw(v) => {
        println(s"${context.self} got Withdraw ${v}")
        balance -= v
        statement += StatementRecord(Withdraw(v), balance)
      }
      case GetStatement()=>{
        println(s"${context.self} got Statement message. Statement is:")
        statement.foreach(record => {
          println(s"${record}")
        })
      }
    }
  }

  val actorSystem = ActorSystem("BankAccountSystem")
  val person = actorSystem.actorOf(Props[Person])
  val bankAccount = actorSystem.actorOf(Props[BankAccount])



  import Person._
  person ! LiveTheLife

  /*
    bankAccount ! Deposite(500)
    bankAccount ! Deposite(500)
    bankAccount ! Withdraw(500)
    */

}

