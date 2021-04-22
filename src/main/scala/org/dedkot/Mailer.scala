package org.dedkot

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import com.typesafe.config.ConfigFactory
import org.dedkot.model.client.Client
import org.dedkot.model.event.Event

import scala.jdk.CollectionConverters.CollectionHasAsScala

object Mailer {

  private val clientsConf = ConfigFactory.load("clients.conf")

  private val clients = clientsConf.getConfigList("clients")
    .asScala.map(Client.configToClient)

  def apply(): Behavior[Event] = Behaviors.receiveMessage { event =>
    // some action for mail send
    clients.filter(client => client.id.equals(event.orgID) && client.subscriptions.contains(event.stage))
      .foreach { client =>
        client.emails.foreach { email =>
          EmailClient.sendSimpleMsg(
            s"Event - ${event.stage}", event.toString, email)
          println(s"Sent email for ${event.orgID} to ${email}")
        }
      }

    Behaviors.same
  }

}
