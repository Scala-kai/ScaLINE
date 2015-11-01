package domain.models

import java.util.Date

import application.Log
import domain.support.{UserId, MessageId, Entity, Tags}

import scalaz.@@

case class Message(
                    id: Int @@ MessageId,
                    from: Int @@ UserId,
                    to: Int @@ UserId,
                    date: Date,
                    body: String,
                    isRead: Boolean
                    ) extends Entity[MessageId] {

  def formalize: Log = Log(date,s"$from send to $to : $body")
}

object Message {
  private[this] var autoInc = 0
  def apply(from: Int @@ UserId, to: Int @@ UserId, body: String): Message = {
    autoInc += 1
    Message(Tags.MessageId(autoInc), from, to, new Date(), body, isRead = false)
  }
}