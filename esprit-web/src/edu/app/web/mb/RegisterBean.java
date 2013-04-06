package edu.app.web.mb;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.validator.ValidatorException;

import edu.app.business.AuthenticationLocal;
import edu.app.business.CustomerServiceLocal;
import edu.app.persistence.Customer;

@ManagedBean
@RequestScoped
public class RegisterBean {
	@EJB
	private CustomerServiceLocal customerServiceLocal;
	@EJB
	private AuthenticationLocal authenticationLocal;
	
	private Customer customer = new Customer();
	private String response = "...";
	
	
	public RegisterBean() {
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public String getResponse() {
		return response;
	}


	public void setResponse(String response) {
		this.response = response;
	}
	
	public void register(AjaxBehaviorEvent event){
		customerServiceLocal.saveOrUpdate(customer);
		response = "you've been sucessfully registred...";
	}
	
	public void validateLogin(FacesContext context, UIComponent component, Object toValidate)
			throws ValidatorException {
		boolean loginExists = false;
		String login = null;
		if (toValidate instanceof String) {
			login = (String) toValidate;
		}
		if ((login.length()!=0) && (login!=null)) {
			loginExists = authenticationLocal.loginExists(login);			
		}
		if (loginExists) {
			FacesMessage message = new FacesMessage("invalid login: someone already took it!");
			throw new ValidatorException(message);
		}

	}

}
