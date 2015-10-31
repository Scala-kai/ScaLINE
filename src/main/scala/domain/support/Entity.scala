package domain.support

import application.Loggable

import scalaz._

trait Entity[ID <: Identifier] extends Loggable {
  val id: Option[Long] @@ ID

  override final def hashCode: Int = 31 * id.##

  override final def equals(that: Any): Boolean = that match {
    case that: Entity[_] => id == that.id
    case _               => false
  }
}