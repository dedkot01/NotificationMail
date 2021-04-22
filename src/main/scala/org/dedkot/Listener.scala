package org.dedkot

import akka.NotUsed
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorRef, Behavior}
import org.dedkot.model.event.Event

object Listener {

  def apply(mailer: ActorRef[Event]): Behavior[NotUsed] = Behaviors.setup { _ =>
    // some action for reading kafka
    while (true) {
      val event = Event.generateRandom

      mailer ! event

      Thread.sleep(15 * 1000)
    }

    Behaviors.empty
  }

}
