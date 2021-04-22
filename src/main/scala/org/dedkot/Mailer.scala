package org.dedkot

import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{Behavior, SupervisorStrategy}
import com.typesafe.config.Config
import org.dedkot.model.client.Client
import org.dedkot.model.event.Event

import scala.jdk.CollectionConverters.CollectionHasAsScala

class Mailer(emailClient: EmailClient, clients: Seq[Client]) {

  private def process: Behavior[Event] = Behaviors.receiveMessage { event =>
    clients.filter(client => client.id.equals(event.orgID) && client.subscriptions.contains(event.stage))
      .foreach { client =>
        client.emails.foreach { email =>
          emailClient.sendSimpleMsg(
            s"Event - ${event.stage}", event.toString, email)
          println(s"Sent email for ${event.orgID} to ${email}")
        }
      }

    Behaviors.same
  }

}

object Mailer {

  def apply(emailClientConfig: Config, clientsConfig: Config): Behavior[Event] = {
    val mailer = new Mailer(
      EmailClient(emailClientConfig),
      clientsConfig.getConfigList("clients").asScala.map(Client.configToClient).toSeq
    )

    Behaviors
      .supervise(Behaviors.supervise(mailer.process)
        .onFailure[IllegalArgumentException](SupervisorStrategy.resume))
      .onFailure(SupervisorStrategy.restart)
  }

}
