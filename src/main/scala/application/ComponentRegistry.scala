package application

import infrastructure.{MessageRepositoryComponent, UserRepositoryComponent}

object ComponentRegistry extends
UserControllerComponent with
UserRepositoryComponent with
MessageControllerComponent with
MessageRepositoryComponent {
  val userController = new UserController
  val messageController = new MessageController

  val userRepository = new UserRepository
  val messageRepository = new MessageRepository
}