package org.dedkot.model.event

import org.scalatest.flatspec._
import org.scalatest.matchers._

import java.time.Instant

class EventSpec extends AnyFlatSpec with should.Matchers {

  "Event" should "return correct String" in {
    val event = Event("0001", "00001", STAGE1, SUCCESS, Instant.ofEpochSecond(1618999810))
    event.toString should be(
      """Пользователь: 00001
        |Этап: STAGE1
        |Статус: SUCCESS
        |Время: 2021-04-21T10:10:10Z""".stripMargin)
  }

}
