package infrastructure

import domain.models.Message
import domain.support.{MessageId, Repository}

import scalaz.@@

class MessageRepository extends Repository[MessageId,Message]{
  def insert(): Unit = ???
  def find(): Message = ???
  def find(id: Int @@ MessageId): Message = ???
  def delete(): Unit = ???
  def update(): Unit = ???
}