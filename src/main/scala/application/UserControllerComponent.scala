package application

import domain.models.User
import domain.support.{Tags, UserId}
import infrastructure.UserRepositoryComponent

import scalaz.@@

trait UserControllerComponent {
  this: UserRepositoryComponent =>

  val userController: UserController

  class UserController {

    def register(name: String, tel: String, email: String): Unit =
      userRepository.insert(User(name,tel,email))

    private def follow(from: Int @@ UserId, to: Int @@ UserId) = {
      userRepository.find(from) foreach { from =>
        if(!from.isFriend(to)) userRepository.update(from.makeFriend(to))
      }
    }

    def makeFriend(from: Int @@ UserId, to: Int @@ UserId) = {
      follow(from, to)
      follow(to, from)
    }

    def login(id: Int): Option[User] = userRepository.find(Tags.UserId(id))

    def findBy(id: Int @@ UserId): Option[User] = userRepository.find(id)

    def findBy(str: String): Option[User] =
      userRepository.find(e=> e.phoneNumber == str || e.email==str)

    def getAll: List[User] = userRepository.all
  }
}