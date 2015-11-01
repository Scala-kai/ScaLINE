package application

import domain.models.User
import domain.support.UserId
import infrastructure.UserRepositoryComponent

import scalaz.@@

trait UserControllerComponent {
  this: UserRepositoryComponent =>

  val userController: UserController

  class UserController {

    def register(name: String, tel: String, email: String): Unit = {
      if(!tel.matches("0\\d{1,4}-\\d{1,4}-\\d{4}") && !tel.matches("0[89]0\\d{8}")){
        println("Invalid phone number...")
      }
      else if(!email.matches("[\\w.\\-]+@[\\w\\-]+\\.[\\w.\\-]+")){
        println("Invalid email address...")
      }
      else if(userRepository.find(_.phoneNumber == tel).isEmpty){
        println("This phone number is already registered.")
      }
      else if(userRepository.find(_.email == email).isEmpty){
        println("This email address is already registered.")
      }
      else {
        userRepository.insert(User(name,tel,email))
        println("You are registered.")
      }
    }

    private def follow(from: Int @@ UserId, to: Int @@ UserId) = {
      userRepository.find(from) foreach { from =>
        if(!from.isFriend(to)) userRepository.update(from.makeFriend(to))
      }
    }

    def makeFriend(from: Int @@ UserId, to: Int @@ UserId) = {
      follow(from, to)
      follow(to, from)
    }

    def getAll: List[User] = userRepository.all
  }
}