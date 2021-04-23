package org.dedkot

import org.apache.commons.mail.{DefaultAuthenticator, SimpleEmail}
import org.dedkot.config.EmailClientConfig

class EmailClient(config: EmailClientConfig) {

  /** Sends the simple email.
   *
   * @param subject          the email subject.
   * @param text             define the content of the mail.
   * @param destinationEmail add a recipient TO to the email.
   * @return the message id of the underlying MimeMessage.
   */
  def sendSimpleMsg(subject: String, text: String, destinationEmail: String): String = {
    val email = new SimpleEmail()

    email.setHostName(config.hostName)
    email.setSmtpPort(config.smtpPort)
    email.setSSLOnConnect(config.SSLOnConnect)
    email.setAuthenticator(new DefaultAuthenticator(
      config.username,
      config.password))
    email.setFrom(config.fromEmail)
    email.setSubject(subject)
      .setMsg(text)
      .addTo(destinationEmail)
      .send
  }

}

object EmailClient {

  def apply(config: EmailClientConfig): EmailClient = new EmailClient(config)

}
