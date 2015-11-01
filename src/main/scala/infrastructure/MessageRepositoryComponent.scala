package infrastructure

import domain.models.Message
import domain.support.{MessageId, Repository}

trait MessageRepositoryComponent{
  val messageRepository: Repository[MessageId,Message]

  class MessageRepository extends Repository[MessageId,Message]{}
}