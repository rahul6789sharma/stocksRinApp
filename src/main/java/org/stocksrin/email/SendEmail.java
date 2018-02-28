package org.stocksrin.email;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/*@Singleton
@Startup*/
public class SendEmail {

/*	@PostConstruct
	public void init() {

		System.out.println("************ sending email ***********");
		sentMail("stocksrin@gmail.com", "bhav Copy Downloaded");
		System.out.println("************ sending email ***********");

	}*/

	public static void sentMail(String toMail, String subject, String body) {

		final String username = "rahul6789sharma@gmail.com";
		final String password = "8971323434@123";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("rahul6789sharma@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toMail));
			message.setSubject(subject);
			message.setText(body);

			Transport.send(message);

			System.out.println("Sent email to :" + toMail);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

}
