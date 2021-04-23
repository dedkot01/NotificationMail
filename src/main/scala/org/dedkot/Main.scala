package org.dedkot

import akka.NotUsed
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorSystem, Behavior}
import com.typesafe.config.ConfigFactory
import org.dedkot.config.{ClientsConfig, EmailClientConfig}

object Guard {

  private val config = ConfigFactory.load

  def apply(): Behavior[NotUsed] = Behaviors.setup { context =>
    val mailer = context.spawn(
      Mailer(EmailClientConfig(config.getConfig("email")),
        ClientsConfig(ConfigFactory.load("clients.conf"))),
      "mailer")
    context.log.info("Mailer has been spawned")

    context.spawn(Listener(mailer.ref), "listener")
    context.log.info("Listener has been spawned")

    Behaviors.empty
  }

}

object Main extends App {
  val actorSystem: ActorSystem[NotUsed] = ActorSystem(Guard(), "Guard")
}
