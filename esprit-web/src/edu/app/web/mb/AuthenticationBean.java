package edu.app.web.mb;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import edu.app.business.AuthenticationLocal;
import edu.app.persistence.User;


@ManagedBean
@SessionScoped
public class AuthenticationBean implements Serializable{

	private static final long serialVersionUID = 5521188282741605406L;
	
	@EJB
	private AuthenticationLocal authenticationLocal;

	private User user = new User();
	
	public AuthenticationBean() {
	}
	
	public String authenticate(){
		String navigateTo = null;
		User found = authenticationLocal.authenticate(user.getLogin(), user.getPassword());
		if (found != null) {
			user = found;
			navigateTo = "main";
		}else {
			navigateTo = "error";
		}
		return navigateTo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
	

}
