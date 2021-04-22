package org.dedkot

import akka.NotUsed
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorSystem, Behavior}
import com.typesafe.config.ConfigFactory

object Guard {

  private val config = ConfigFactory.load

  def apply(): Behavior[NotUsed] = Behaviors.setup { context =>
    val clientsConfig = ConfigFactory.load("clients.conf")
    val mailer = context.spawn(Mailer(config.getConfig("email"), clientsConfig),
      "mailer")

    val listener = context.spawn(Listener(mailer.ref), "listener")

    Behaviors.empty
  }

}

object Main extends App {
  val actorSystem: ActorSystem[NotUsed] = ActorSystem(Guard(), "Guard")
}
