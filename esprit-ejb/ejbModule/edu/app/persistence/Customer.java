package edu.app.persistence;

import edu.app.persistence.User;
import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import javax.persistence.*;

@Entity
public class Customer extends User implements Serializable {

	private static final long serialVersionUID = -1100346515700490749L;
	
	private String firstname;
	private String lastname;
	private String phoneNumber;
	private Date birthDate;
	

	public Customer() {
	} 
	
	
	public Customer(String login, String password, String email,String firstname, String lastname, String phoneNumber, Date birthDate) {
		super(login, password, email);
		this.firstname = firstname;
		this.lastname = lastname;
		this.phoneNumber = phoneNumber;
		this.birthDate = birthDate;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}   
	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	@Column(name = "phone_number")
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "birth_date")
	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
   
}
