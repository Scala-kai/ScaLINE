package domain.models

import java.util.Date

import application.{Log, Loggable}
import domain.support.UserId

import scalaz.@@

case class Friend(friendId: Int @@ UserId, date: Date) extends Loggable {
  def formalize: Log = Log(date, s"$friendId")
  def formalize(userId: Int @@ UserId): Log = Log(date, s"$userId became a friend $friendId")
}