package org.stocksrin.email;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.DateUtils;
import org.stocksrin.utils.LoggerSysOut;

public class SendEmail {

	private static boolean sendEmail = false;
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

		try {
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

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("rahul6789sharma@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toMail));
			message.setSubject(subject);
			String str = DateUtils.dateToString(new Date(), APPConstant.DATEFORMATE_Logger);
			String finalBody = body + "\n \n" + "*******************\n\n" + hostName + "\n********** \n" + str;

			message.setText(finalBody);

			if (sendEmail) {
				Transport.send(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		LoggerSysOut.print("Sent email to :" + toMail);

	}

}
