package application

import domain.models.Message
import domain.support.UserId
import infrastructure.MessageRepositoryComponent

import scalaz.@@

trait MessageControllerComponent {
  this: MessageRepositoryComponent =>

  val messageController: MessageController

  class MessageController {
    def getTalk(a: Int @@ UserId, b: Int @@ UserId) = ???

    def post(from: Int @@ UserId, to: Int @@ UserId, message: String) =
      messageRepository.insert(Message(from,to,message))

    def getAll: List[Message] = messageRepository.all
  }
}