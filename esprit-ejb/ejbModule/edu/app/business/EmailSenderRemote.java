package edu.app.business;

import java.util.concurrent.Future;

import javax.ejb.Remote;

@Remote
public interface EmailSenderRemote {
	
	Future<String> sendMail(String to, String subject, String messageBoby);

}
