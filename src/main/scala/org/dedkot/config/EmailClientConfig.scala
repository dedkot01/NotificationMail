package org.dedkot.config

import com.typesafe.config.Config

case class EmailClientConfig(hostName: String,
                             smtpPort: Int,
                             SSLOnConnect: Boolean,
                             fromEmail: String,
                             username: String,
                             password: String)

object EmailClientConfig {

  def apply(config: Config): EmailClientConfig =
    EmailClientConfig(
      config.getString("hostName"),
      config.getInt("smtpPort"),
      config.getBoolean("SSLOnConnect"),
      config.getString("from"),
      config.getString("authenticator.userName"),
      config.getString("authenticator.password")
    )

}
