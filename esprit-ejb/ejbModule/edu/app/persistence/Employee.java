package edu.app.persistence;

import java.io.Serializable;

import javax.persistence.Entity;
@Entity
public class Employee extends User implements Serializable{

	private static final long serialVersionUID = 2266212277802018650L;
	
	
	public Employee() {
	}

	public Employee(String login, String password, String email) {
		super(login, password, email);
	}


	
	
	

}
