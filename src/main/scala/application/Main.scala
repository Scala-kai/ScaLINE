package application

import domain.models.User
import domain.support.Tags

import scala.io.StdIn.readLine

object StringUtils {
  implicit class StringImprovements(val s: String) {
    import scala.util.control.Exception._
    def toIntOpt = catching(classOf[NumberFormatException]) opt s.toInt
  }
}

object Main {
  import StringUtils._

  val userController = ComponentRegistry.userController
  val messageController = ComponentRegistry.messageController

  private[this] var loginUser: Option[User] = None

  def main(args: Array[String]): Unit = {
    val lines = Iterator.continually(readLine()).takeWhile(_ != null)

    for((line, lineNum) <- lines.zipWithIndex) {
      val a = line.split(' ')
      a(0) match {
        case "login" => {
          println("Please input the login user id:")
          val id: Option[Int] = readLine().toIntOpt
          loginUser = userController.login(Tags.UserId(id.getOrElse(-1)))
          if(loginUser.isEmpty)println("The user id is not exists.")
          else println(s"You logged in $loginUser")
        }
        case "register" => {
          println("Please input your name:")
          val name = readLine()

          println("phone number:")
          val tel = readLine()

          println("email address:")
          val email = readLine()

          if(!tel.matches("0\\d{1,4}-\\d{1,4}-\\d{4}") && !tel.matches("0[89]0\\d{8}")){
            println("Invalid phone number...")
          }
          else if(!email.matches("[\\w.\\-]+@[\\w\\-]+\\.[\\w.\\-]+")){
            println("Invalid email address...")
          }
          else if(userController.findBy(tel).isDefined){
            println("This phone number is already registered...")
          }
          else if(userController.findBy(email).isDefined){
            println("This email address is already registered...")
          }
          else {
            userController.register(name, tel, email)
            println("You are registered.")
          }
        }
        case "makeFriend" => {
          if(loginUser.isEmpty)println("Please login.")
          else {
            println("Please input the target ID")
            val targetId = Tags.UserId(readLine().toIntOpt.getOrElse(-1))
            if(userController.findBy(targetId).isEmpty)println("The user id is not exists.")
            else userController.makeFriend(loginUser.getOrElse(User.empty).id, targetId)
          }
        }
        case "talk" => {
          if(loginUser.isEmpty)println("Please login.")
          else {
            println("Please input the target ID:")
            val to = Tags.UserId(readLine().toIntOpt.getOrElse(-1))
            println("Please input a message without any blanks:")
            val msg = readLine()
            messageController.post(loginUser.getOrElse(User.empty).id, to, msg)
          }
        }
        case "list" => {
          println("all user:")
          userController.getAll.foreach(println)
        }
        case "log" => {
          println("log")
        }
        case _ => println("invalid...")
      }
    }
  }

}
