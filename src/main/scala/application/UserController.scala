package application

import domain.models.User
import domain.support.UserId

import scalaz.@@

object UserController {

  def register(name: String, tel: String, email: String): Unit = ???

  def makeFriend(from: Int @@ UserId, to: Int @@ UserId) = ???

  def getAll: List[User] = ???
}