package test;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;

public class TestMail {

	public static void main(String[] args) {
		try {
			String remitente = "ausy.soporte@gmail.com";
			String receptor = "cristian.parada@uptc.edu.co";
			String password = "AuSyDC2022";
			Properties props = new Properties();
			props.setProperty("mail.smtp.host", "smtp.gmail.com");
			props.setProperty("mail.smtp.starttls.enable", "true");
			props.setProperty("mail.smtp.port", "587");
			props.setProperty("mail.smtp.user", "remitente");
			props.setProperty("mail.smtp.auth", "true");
			Session session = Session.getDefaultInstance(props, null);
			
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(remitente));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receptor));
			message.setSubject("ESTE ES UN MENSAJE DE CONFIDENCIALIDAD");
			BodyPart adjunto = new MimeBodyPart();
			adjunto.setDataHandler(new DataHandler(new FileDataSource("res/logoOscuro.png")));
			adjunto.setFileName("logo.png");
			BodyPart text = new MimeBodyPart();
			text.setText("Atencion, la aplicación Augusto Systems esta completamente configurada para realizar seguimiento de los desarrolladores registrados en el sistema, por favor no responda a este correo");
			MimeMultipart multiParte = new MimeMultipart();
			multiParte.addBodyPart(text);
			multiParte.addBodyPart(adjunto);
			message.setContent(multiParte);
			Transport t = session.getTransport("smtp");
			t.connect(remitente, password);
			t.sendMessage(message, message.getAllRecipients());
			t.close();
			System.out.println("envio exitoso");
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
}
