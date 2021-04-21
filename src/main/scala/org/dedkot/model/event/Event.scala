package org.dedkot.model.event

import org.dedkot.model.event.Stage.Stage
import org.dedkot.model.event.Status.Status

import java.time.Instant

case class Event(userID: String,
                 stage: Stage,
                 status: Status,
                 dateTime: Instant) {

  override def toString: String = {
    s"""Пользователь: ${userID.substring(5)}
       |Этап: ${stage}
       |Статус: ${status}
       |Время: ${dateTime}""".stripMargin
  }

}

object Stage extends Enumeration {
  type Stage = Value
  val STAGE1, STAGE2, STAGE3, STAGE4 = Value
}

object Status extends Enumeration {
  type Status = Value
  val SUCCESS, FAILURE, GENERAL = Value
}
