package org.dedkot.config

import com.typesafe.config.Config
import org.dedkot.model.client.Client

import scala.jdk.CollectionConverters.CollectionHasAsScala

case class ClientsConfig(clients: Seq[Client])

object ClientsConfig {

  def apply(config: Config): ClientsConfig =
    ClientsConfig(config.getConfigList("clients").asScala.map(Client(_)).toSeq)

}
