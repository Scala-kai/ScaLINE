package domain.models

import java.util.Date

import application.Log
import domain.support.{Entity ,Tags, UserId}

import scalaz.@@

case class User(
                 id: Int @@ UserId,
                 name: String,
                 phoneNumber: String,
                 email: String,
                 registerDate: Date,
                 friends: List[Friend]
                 ) extends Entity[UserId] {

  def formalize: Log = Log(registerDate, s"$name registered")

  def talk(to: Int @@ UserId, msg: String): Message = Message(id, to, msg)

  def makeFriend(to: Int @@ UserId): User = copy(friends = Friend(to)::friends)

  def isFriend(to: Int @@ UserId): Boolean = friends.exists(_.friendId == to)

  def read(msg: Message): Message = msg.copy(isRead = true)
}

object User {
  private[this] var autoInc: Int = 0

  def apply(name:String, phoneNumber: String, email:String): User = {
    autoInc+=1
    User(Tags.UserId(autoInc),name,phoneNumber,email,new Date(),List[Friend]())
  }
}