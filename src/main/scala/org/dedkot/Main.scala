package org.dedkot

import akka.NotUsed
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorSystem, Behavior}

object Guard {

  def apply(): Behavior[NotUsed] = Behaviors.setup { context =>
    val mailer = context.spawn(Mailer(), "mailer")
    val listener = context.spawn(Listener(mailer.ref), "listener")

    Behaviors.empty
  }

}

object Main extends App {
  val actorSystem: ActorSystem[NotUsed] = ActorSystem(Guard(), "Guard")
}
