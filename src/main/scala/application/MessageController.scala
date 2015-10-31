package application

import domain.models.Message
import domain.support.UserId

import scalaz.@@

object MessageController {

  def getTalk(a: Int @@ UserId, b: Int @@ UserId) = ???

  def post(from: Int @@ UserId, to: Int @@ UserId, message: String) = ???

  def getAll: List[Message] = ???
}