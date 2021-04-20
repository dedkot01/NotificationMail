package org.dedkot

import akka.actor.testkit.typed.scaladsl.ScalaTestWithActorTestKit
import Greeter.Greet
import Greeter.Greeted
import org.dedkot.Greeter.Greeted
import org.scalatest.wordspec.AnyWordSpecLike

class AkkaQuickstartSpec extends ScalaTestWithActorTestKit with AnyWordSpecLike {

  "A Greeter" must {

    "reply to greeted" in {
      val replyProbe = createTestProbe[Greeted]()
      val underTest = spawn(Greeter())
      underTest ! Greet("Santa", replyProbe.ref)
      replyProbe.expectMessage(Greeted("Santa", underTest.ref))
    }

  }

}
