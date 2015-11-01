package infrastructure

import domain.models.User
import domain.support.{UserId, Repository}

trait UserRepositoryComponent {
  val userRepository: Repository[UserId, User]

  class UserRepository extends Repository[UserId, User]{}
}