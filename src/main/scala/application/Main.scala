package application

import scala.io.StdIn.readLine

object Main {
  def main(args: Array[String]): Unit = {
    val lines = Iterator.continually(readLine()).takeWhile(_ != null)
    for((line, lineNum) <- lines.zipWithIndex) {
      val a = line.split(' ')
      a(0) match {
        case "register" => {
          println("please input your name, phone number, email address")
        }
        case "makeFriend" => {
          println("please input the target ID")
        }
        case "talk" => {
          println("please input the target ID and a message without any blanks.")
        }
        case "list" => {
          println("all user:")
        }
        case "log" => {
          println("log")
        }
        case _ => println("invalid...")
      }
    }
  }
}
