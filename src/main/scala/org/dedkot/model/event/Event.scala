package org.dedkot.model.event

import java.time.Instant

final case class Event(orgID: String,
                       userID: String,
                       stage: Stage,
                       status: Status,
                       dateTime: Instant) {

  override def toString: String =
    s"""Пользователь: ${userID}
       |Этап: ${stage}
       |Статус: ${status}
       |Время: ${dateTime}""".stripMargin

}

object Event {

  def createRandom: Event = {
    import scala.util.Random

    Event(
      s"000${1 + Random.nextInt(4)}",
      s"0000${1 + Random.nextInt(10)}",
      Random.nextInt(4) match {
        case 0 => STAGE1
        case 1 => STAGE2
        case 2 => STAGE3
        case 3 => STAGE4
        case _ => STAGE1
      },
      Random.nextInt(3) match {
        case 0 => SUCCESS
        case 1 => FAILURE
        case 2 => GENERAL
        case _ => SUCCESS
      },
      Instant.now()
    )
  }

}

sealed trait Stage
case object STAGE1 extends Stage
case object STAGE2 extends Stage
case object STAGE3 extends Stage
case object STAGE4 extends Stage

sealed trait Status
case object SUCCESS extends Status
case object FAILURE extends Status
case object GENERAL extends Status
