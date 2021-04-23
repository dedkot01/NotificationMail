package org.dedkot

import akka.NotUsed
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorRef, Behavior, SupervisorStrategy}
import org.dedkot.model.event.Event

object Listener {

  def apply(mailer: ActorRef[Event]): Behavior[NotUsed] = Behaviors.supervise[NotUsed] {
    Behaviors.setup { context =>
      while (true) {
        val event = Event.createRandom
        context.log.info(s"Received event: ${event.orgID}-${event.userID} ${event.stage}")

        mailer ! event

        Thread.sleep(15 * 1000)
      }

      Behaviors.empty
    }
  }.onFailure(SupervisorStrategy.restart)

}
