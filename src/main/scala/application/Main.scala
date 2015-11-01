package application

import domain.models.{Friend, User}
import domain.support.Tags
import java.io.PrintWriter
import scala.io.StdIn.readLine

object Main {
  import StringUtils._

  val userController = ComponentRegistry.userController
  val messageController = ComponentRegistry.messageController

  private[this] var loginUser: Option[User] = None

  def main(args: Array[String]): Unit = {
    val lines = Iterator.continually(readLine()).takeWhile(_ != null)
    println("Please input a command:")
    for((line, lineNum) <- lines.zipWithIndex) {
      line.split(' ')(0) match {
        case "login" => {
          println("Please input the login user id:")
          val id = readLine().toUserId
          loginUser = userController.login(id)
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
            val targetId = readLine().toUserId
            if(userController.findBy(targetId).isEmpty)println("The user id is not exists.")
            else userController.makeFriend(loginUser.getOrElse(User.empty).id, targetId)
          }
        }
        case "talk" => {
          if(loginUser.isEmpty)println("Please login.")
          else {
            println("Please input the target ID:")
            val to = readLine().toUserId
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
          println("Please input the filename:")
          val filename = readLine()
          val file = new PrintWriter(filename)
          val allUser = userController.getAll
          val allMsg = messageController.getAll
          val allFriend: List[Friend] = allUser.foldLeft(List[Friend]())(_ ++ _.friends)
          val allLog: List[Log] = allUser.map(_.formalize) ++ allMsg.map(_.formalize) ++ allFriend.map(_.formalize)
          allLog.foreach(log => file.write(log.toString))
          file.close()
          println("log")
        }
        case _ => println("invalid...")
      }
      println("Please input a command:")
    }
  }
}

object StringUtils {
  implicit class StringImprovements(val s: String) {
    import scala.util.control.Exception._
    def toIntOpt = catching(classOf[NumberFormatException]) opt s.toInt
    def toUserId = Tags.UserId((catching(classOf[NumberFormatException]) opt s.toInt).getOrElse(-1))
  }
}