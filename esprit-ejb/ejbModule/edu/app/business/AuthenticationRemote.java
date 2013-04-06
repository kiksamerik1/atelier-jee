package edu.app.business;

import java.util.List;

import javax.ejb.Remote;

import edu.app.persistence.Employee;
import edu.app.persistence.User;


@Remote
public interface AuthenticationRemote {
	void createUser(User user);
	List<User> findAllUsers();
	User authenticate(String login, String password);
	List<Employee> findAllEmployees();
	void saveOrUpdate(Employee employee);
}
