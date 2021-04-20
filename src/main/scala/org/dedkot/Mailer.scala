package org.dedkot

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors

object Mailer {

  final case class Message(str: String)

  def apply(): Behavior[Message] = Behaviors.receiveMessage { msg =>
    // some action for mail send
    println("I got msg, look! " + msg.str)

    Behaviors.same
  }

}
