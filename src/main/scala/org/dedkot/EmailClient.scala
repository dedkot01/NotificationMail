package org.dedkot

import com.typesafe.config.ConfigFactory
import org.apache.commons.mail.{DefaultAuthenticator, SimpleEmail}

/** Singleton object. Implements sending email messages.
 *
 * Configurable in application.conf.
 */
object EmailClient {

  private val conf = ConfigFactory.load()

  private val email = new SimpleEmail()

  email.setHostName(conf.getString("email.hostName"))
  email.setSmtpPort(conf.getInt("email.smtpPort"))
  email.setSSLOnConnect(conf.getBoolean("email.SSLOnConnect"))
  email.setAuthenticator(new DefaultAuthenticator(
    conf.getString("email.authenticator.userName"),
    conf.getString("email.authenticator.password")))
  email.setFrom(conf.getString("email.from"))

  /** Sends the simple email.
   *
   *  @param subject the email subject.
   *  @param text define the content of the mail.
   *  @param destinationEmail add a recipient TO to the email.
   *  @return the message id of the underlying MimeMessage.
   */
  def sendSimpleMsg(subject: String, text: String, destinationEmail: String): String = {
    email.setSubject(subject)
      .setMsg(text)
      .addTo(destinationEmail)
      .send
  }

}
