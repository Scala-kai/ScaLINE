package domain.support

import scala.collection.mutable
import scalaz.@@

trait Repository[ID <: Identifier, E <: Entity[ID]]{
  type This <: Repository[ID, E]

  protected val queue: mutable.Set[Int] = mutable.Set[Int]()

  def insert(): Unit
  def find(): E
  def find(id: Int @@ ID): E
  def delete(): Unit
  def update(): Unit
}