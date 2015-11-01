package application

import domain.models.Message
import domain.support.UserId
import infrastructure.MessageRepositoryComponent

import scalaz.@@

trait MessageControllerComponent {
  this: MessageRepositoryComponent =>

  val messageController: MessageController

  class MessageController {

    def post(from: Int @@ UserId, to: Int @@ UserId, message: String): Unit =
      messageRepository.insert(Message(from,to,message))

    def getTalk(a: Int @@ UserId, b: Int @@ UserId): List[Message] =
      messageRepository.filter(e => (e.from==a && e.to==b) || (e.from==b && e.to==a)).sortBy(_.date)

    def getAll: List[Message] = messageRepository.all
  }
}