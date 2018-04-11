package org.stocksrin.email;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

	private static boolean sendEmail = true;
	private static String toMail = "stocksrin@gmail.com";

	static String hostName;

	static {
		try {
			hostName = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public static void sentMail(String subject, String body) {

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
			String finalBody = body + "\n \n" + "*******************\n\n" + hostName;
			message.setText(finalBody);

			if (sendEmail) {
				Transport.send(message);
			}

			System.out.println("Sent email to :" + toMail);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

}
