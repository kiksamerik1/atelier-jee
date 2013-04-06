package edu.app.web.mb;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import edu.app.business.AuthenticationLocal;
import edu.app.persistence.Admin;
import edu.app.persistence.Customer;
import edu.app.persistence.User;

@ManagedBean
@SessionScoped
public class AuthenticationBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6710404278650523921L;
	
	@EJB
	private AuthenticationLocal authenticationLocal;
	
	private User user = new User();
	private boolean loggedIn = false;
	private String userType;
	
	public AuthenticationBean() {
	}
	
	public String login(){
		String navigateTo = null;
		User found = authenticationLocal.authenticate(user.getLogin(), user.getPassword());
		if (found == null) {
			user.setPassword(null);
			setLoggedIn(false);
			userType = null;
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bad credentials", "verify your login and password!");
			FacesContext.getCurrentInstance().addMessage("form_sign_in:submit_login", message);
		} else {
			setLoggedIn(true);
			if (found instanceof Admin) {
				userType = "Admin";
				navigateTo = "/pages/administration";
			}
			if (found instanceof Customer) {
				userType = "Customer";
				navigateTo = "/pages/shop";
			}
		}
		return navigateTo;
	}
	
	public String logout(){
		String navigateTo = "/welcome";
		user = new User();
		setLoggedIn(false);
		userType = null;
		return navigateTo;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public boolean isLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	
	

}
