package org.dedkot.model.event

import org.dedkot.model.event.Stage.Stage
import org.dedkot.model.event.Status.Status

import java.time.Instant

final case class Event(userID: String,
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

object Event {

  def generateRandom = {
    import scala.util.Random

    Event(
      s"000${1 + Random.nextInt(4)}-0000${1 + Random.nextInt(4)}",
      Random.nextInt(4) match {
        case 0 => Stage.STAGE1
        case 1 => Stage.STAGE2
        case 2 => Stage.STAGE3
        case 3 => Stage.STAGE4
        case _ => Stage.STAGE1
      },
      Random.nextInt(3) match {
        case 0 => Status.SUCCESS
        case 1 => Status.FAILURE
        case 2 => Status.GENERAL
        case _ => Status.SUCCESS
      },
      Instant.now()
    )
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
