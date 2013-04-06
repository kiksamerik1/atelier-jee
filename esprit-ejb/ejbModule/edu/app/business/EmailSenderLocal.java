package edu.app.business;

import java.util.concurrent.Future;

import javax.ejb.Local;

@Local
public interface EmailSenderLocal {
	Future<String> sendMail(String to, String subject, String messageBoby);
}
