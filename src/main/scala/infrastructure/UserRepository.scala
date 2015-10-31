package infrastructure

import domain.models.User
import domain.support.{UserId, Repository}

import scalaz.@@

class UserRepository extends Repository[UserId,User]{
  def insert(): Unit = ???
  def find(): User = ???
  def find(id: Int @@ UserId): User = ???
  def delete(): Unit = ???
  def update(): Unit = ???
}