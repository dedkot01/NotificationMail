package org.dedkot

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import com.typesafe.config.ConfigFactory
import org.dedkot.model.event.Event

import scala.jdk.CollectionConverters.CollectionHasAsScala

object Mailer {

  private val clientsConf = ConfigFactory.load("clients.conf")

  private val emails = clientsConf.getConfig("clients").entrySet
    .asScala.map(e => e.getKey -> e.getValue.render().substring(1, e.getValue.render().length - 1)).toMap

  def apply(): Behavior[Event] = Behaviors.receiveMessage { event =>
    // some action for mail send
    emails.foreach { client =>
      EmailClient.sendSimpleMsg(s"Event - ${event.stage}", event.toString, client._2)
      println(s"Sent email to ${client._2} from ${event.userID}")
    }

    Behaviors.same
  }

}
