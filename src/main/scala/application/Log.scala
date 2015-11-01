package application

import java.util.Date

case class Log(date: Date, details: String)

object Log {
  def apply(details: String): Log = Log(new Date(), details)
}