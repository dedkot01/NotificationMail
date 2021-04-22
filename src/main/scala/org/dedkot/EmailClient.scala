package org.dedkot

import com.typesafe.config.{Config, ConfigFactory}
import org.apache.commons.mail.{DefaultAuthenticator, SimpleEmail}

class EmailClient(config: Config) {

  /** Sends the simple email.
   *
   * @param subject          the email subject.
   * @param text             define the content of the mail.
   * @param destinationEmail add a recipient TO to the email.
   * @return the message id of the underlying MimeMessage.
   */
  def sendSimpleMsg(subject: String, text: String, destinationEmail: String): String = {
    val email = new SimpleEmail()

    email.setHostName(config.getString("hostName"))
    email.setSmtpPort(config.getInt("smtpPort"))
    email.setSSLOnConnect(config.getBoolean("SSLOnConnect"))
    email.setAuthenticator(new DefaultAuthenticator(
      config.getString("authenticator.userName"),
      config.getString("authenticator.password")))
    email.setFrom(config.getString("from"))
    email.setSubject(subject)
      .setMsg(text)
      .addTo(destinationEmail)
      .send
  }

}

object EmailClient {

  def apply(config: Config): EmailClient = new EmailClient(config)

}
