package domain.support

import scalaz._

object Tags {
  val UserId = Tag.of[UserId]
  val MessageId = Tag.of[MessageId]
}