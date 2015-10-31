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
}

object User {
  private[this] var autoInc: Int = 0

  def apply(name:String, phoneNumber: String, email:String): User = {
    autoInc+=1
    User(Tags.UserId(autoInc),name,phoneNumber,email,new Date(),List[Friend]())
  }
}