package domain.support

import scala.collection.mutable
import scalaz.@@

trait Repository[ID <: Identifier, E <: Entity[ID]]{
  type This <: Repository[ID, E]

  protected val set: mutable.Set[E] = mutable.Set[E]()

  def insert(e: E): Unit = set += e

  def find(e: E): Option[E] = set.find(_ == e)

  def find(id: Int @@ ID): Option[E] = set.find(_.id == id)

  def find(p: E => Boolean): Option[E] = set.find(p)

  def filter(p: E => Boolean): List[E] = set.filter(p).toList

  def delete(e: E): Unit = set -= e

  def delete(id: Int @@ ID): Unit = set.retain(_.id == id)

  def update(e: E): Unit = {
    delete(e)
    insert(e)
  }

  def all: List[E] = set.toList
}