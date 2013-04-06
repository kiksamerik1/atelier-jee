package edu.app.business;

import java.util.Date;
import java.util.concurrent.Future;

import javax.annotation.Resource;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Stateless
public class EmailSender implements EmailSenderRemote, EmailSenderLocal{
	
	@Resource(mappedName = "java:/shop-mail")
	private Session session; 

    public EmailSender() {
    }

    @Asynchronous
	public Future<String> sendMail(String to, String subject, String messageBoby) {
    	String status;
		Message message = new MimeMessage(session);
		try {
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSentDate(new Date());
			message.setSubject(subject);
			message.setText(messageBoby);
			
			Transport.send(message);
			status = "sent";
			
		} catch (MessagingException e) {
			status = "encoutred an error";
			throw new RuntimeException(e);
		}
		return new AsyncResult<String>(status);
	}
    
    

}
