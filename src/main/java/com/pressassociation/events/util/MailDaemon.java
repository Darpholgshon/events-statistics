package com.pressassociation.events.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.PostConstruct;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;

/**
 * ****************************************************************************************
 *
 * @author <a href="ralph.hodgson@pressassociation.com">Ralph Hodgson</a>
 * @since 05/09/2014 15:21
 * <p/>
 * ****************************************************************************************
 */
@Component
@PropertySource("classpath:application.properties")
public class MailDaemon {

  @Autowired
  Environment env;

  Properties props = new Properties();

  @PostConstruct
  public void configureMailDaemon(){
    props.put("mail.smtp.auth", env.getProperty("mail.smtp.auth"));
    props.put("mail.smtp.starttls.enable", env.getProperty("mail.smtp.starttls.enable"));
    props.put("mail.smtp.host", env.getProperty("mail.smtp.host"));
    props.put("mail.smtp.port", env.getProperty("mail.smtp.port"));
  }

  public synchronized void sendMessage(String subjectText, String messageHTML)
          throws Exception {
    sendMessage(subjectText, messageHTML, new File[]{});
  }

  public synchronized void sendMessage(String subjectText, String messageHTML, File... attachments)
          throws Exception {
    // Get the default Session object.
    Session session = Session.getDefaultInstance(props,
                                                 new javax.mail.Authenticator() {
                                                   protected PasswordAuthentication getPasswordAuthentication() {
                                                     return new PasswordAuthentication(env.getProperty("mail.user"),
                                                                                       env.getProperty("mail.pswd"));
                                                   }
                                                 });

    // Create a default MimeMessage object.
    MimeMessage message = new MimeMessage(session);

    String[] recipients = env.getProperty("mail.recipients", String[].class);

    for (String recipient: recipients) {
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
    }
    // Set Subject: header field
    message.setSubject(subjectText);

    MimeBodyPart bodyPart = new MimeBodyPart();
    bodyPart.setContent(messageHTML, "text/html; charset=utf-8");

    Multipart multipart = new MimeMultipart();
    multipart.addBodyPart(bodyPart);

    if (attachments != null){
        for (File f : attachments){
          bodyPart = new MimeBodyPart();
          DataSource source = new FileDataSource(f);
          bodyPart.setDataHandler(new DataHandler(source));
          bodyPart.setFileName(f.getName());
          multipart.addBodyPart(bodyPart);
      }
    }
    message.setContent(multipart);

    // Send message
    Transport.send(message);
    System.out.println("Sent message successfully....");
  }

}
