package infrastructure

import domain.models.Message
import domain.support.{MessageId, Repository}

object MessageRepository{
  val repo = new Repository[MessageId,Message]{}

}