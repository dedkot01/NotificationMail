package org.dedkot.model.client

import com.typesafe.config.Config
import org.dedkot.model.event._

import scala.jdk.CollectionConverters.ListHasAsScala

case class Client(id: String,
                  emails: Seq[String],
                  subscriptions: Seq[Stage])

object Client {

  def configToClient(config: Config): Client =
    Client(config.getString("id"),
      config.getStringList("emails").asScala.toSeq,
      config.getStringList("subscriptions").asScala.toSeq.map {
        case "STAGE1" => STAGE1
        case "STAGE2" => STAGE2
        case "STAGE3" => STAGE3
        case "STAGE4" => STAGE4
        case _ => STAGE1
      })

}
