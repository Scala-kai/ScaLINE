package application

import infrastructure.{MessageRepositoryComponent, UserRepositoryComponent}

object ComponentRegistry extends
UserControllerComponent with
UserRepositoryComponent with
MessageControllerComponent with
MessageRepositoryComponent {
  val userController = new UserController
  val userRepository = new UserRepository
  val messageController = new MessageController
  val messageRepository = new MessageRepository
}