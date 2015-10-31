package domain.support

sealed trait Identifier
trait UserId extends Identifier
trait MessageId extends Identifier
