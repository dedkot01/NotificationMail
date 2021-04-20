package org.dedkot

import com.typesafe.config.ConfigFactory
import org.apache.commons.mail.{DefaultAuthenticator, SimpleEmail}

object EmailClient {

  val conf = ConfigFactory.load()

  val email = new SimpleEmail()

  email.setHostName(conf.getString("email.hostName"))
  email.setSmtpPort(conf.getInt("email.smtpPort"))
  email.setSSLOnConnect(conf.getBoolean("email.SSLOnConnect"))
  email.setAuthenticator(new DefaultAuthenticator(
    conf.getString("email.authenticator.userName"),
    conf.getString("email.authenticator.password")))
  email.setFrom(conf.getString("email.from"))

  def sendSimpleMsg(subject: String, text: String, destinationEmail: String): String = {
    email.setSubject(subject)
      .setMsg(text)
      .addTo(destinationEmail)
      .send
  }

}
