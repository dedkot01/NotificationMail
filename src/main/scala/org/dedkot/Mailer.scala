package org.dedkot

import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{Behavior, SupervisorStrategy}
import org.dedkot.config.{ClientsConfig, EmailClientConfig}
import org.dedkot.model.client.Client
import org.dedkot.model.event.Event

class Mailer(emailClient: EmailClient, clients: Seq[Client]) {

  private def process: Behavior[Event] = Behaviors.receive { (context, event) =>
    context.log.info(s"Received event: ${event.orgID}-${event.userID} ${event.stage}")

    clients.filter(client => client.id.equals(event.orgID) && client.subscriptions.contains(event.stage))
      .foreach { client =>
        client.emails.foreach { email =>
          emailClient.sendSimpleMsg(
            s"Event - ${event.stage}", event.toString, email)

          context.log.info(s"Sent email to ${email} with event: ${event.orgID}-${event.userID} ${event.stage}")
        }
      }

    Behaviors.same
  }

}

object Mailer {

  def apply(emailClientConfig: EmailClientConfig, clientsConfig: ClientsConfig): Behavior[Event] = {
    val mailer = new Mailer(
      EmailClient(emailClientConfig),
      clientsConfig.clients
    )

    Behaviors
      .supervise(Behaviors.supervise(mailer.process)
        .onFailure[IllegalArgumentException](SupervisorStrategy.resume))
      .onFailure(SupervisorStrategy.restart)
  }

}
