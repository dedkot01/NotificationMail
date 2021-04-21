package org.dedkot

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import org.dedkot.model.event.Event

object Mailer {

  def apply(): Behavior[Event] = Behaviors.receiveMessage { event =>
    // some action for mail send
    println("I got msg, look!\n" + event)

    Behaviors.same
  }

}
