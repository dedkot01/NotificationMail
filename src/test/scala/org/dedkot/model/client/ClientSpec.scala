package org.dedkot.model.client

import com.typesafe.config.ConfigFactory
import org.dedkot.model.event.{STAGE1, STAGE2, STAGE3, STAGE4}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class ClientSpec extends AnyFlatSpec with should.Matchers {

  "Client" should "reading config" in {
    val config = ConfigFactory.load("clients.conf")

    val clientFromConfig = Client(config.getConfig("test.test"))
    val clientExpected = Client("test", Seq("test@test.test"), Seq(STAGE1, STAGE2, STAGE3, STAGE4))

    clientFromConfig should be (clientExpected)
  }

}
