package edu.app.web.mb;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import edu.app.business.AuthenticationLocal;
import edu.app.persistence.User;


@ManagedBean
@SessionScoped
public class AuthenticationBean implements Serializable{

	private static final long serialVersionUID = 5521188282741605406L;
	
	@EJB
	private AuthenticationLocal authenticationLocal;

	private User user = new User();
	private boolean loggedIn = false;
	
	public AuthenticationBean() {
	}
	
	public String login(){
		String navigateTo = null;
		User found = authenticationLocal.authenticate(user.getLogin(), user.getPassword());
		if (found != null) {
			user = found;
			loggedIn = true;
			navigateTo = "main";
		}else {
			loggedIn = false;
			FacesMessage message = new FacesMessage("Bad credentials");
			FacesContext.getCurrentInstance().addMessage("form_login:submit_login", message);
			navigateTo = null;
		}
		return navigateTo;
	}
	
	public String logout(){
		String navigateTo = null;
		loggedIn = false;
		user = new User();
		navigateTo = "/welcome";
		return navigateTo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	
	
	
	

}
