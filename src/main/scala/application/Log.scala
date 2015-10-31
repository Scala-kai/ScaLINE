package application

import java.util.Date

case class Log(date: Date, details: String)

object Log {
  def apply(details: String) = Log(new Date(), details)
}