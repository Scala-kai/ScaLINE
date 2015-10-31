package infrastructure

import domain.models.User
import domain.support.{UserId, Repository}

object UserRepository {
  val repo = new Repository[UserId, User]{}
}