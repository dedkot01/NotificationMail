package org.dedkot

import akka.NotUsed
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorRef, Behavior}

object Listener {

  def apply(mailer: ActorRef[Mailer.Message]): Behavior[NotUsed] = Behaviors.setup { _ =>
    // some action for reading kafka
    while (true) {
      mailer ! Mailer.Message("Hello, mailer. I'm listener, have a good day ^^")

      Thread.sleep(5 * 1000)
    }

    Behaviors.empty
  }

}
