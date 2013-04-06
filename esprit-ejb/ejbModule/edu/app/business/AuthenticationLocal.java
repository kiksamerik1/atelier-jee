package edu.app.business;

import java.util.List;

import javax.ejb.Local;

import edu.app.persistence.Employee;
import edu.app.persistence.User;
@Local
public interface AuthenticationLocal {
	void createUser(User user);
	List<User> findAllUsers();
	User authenticate(String login, String password);
	List<Employee> findAllEmployees();
	void saveOrUpdate(Employee employee);
	boolean loginExists(String login);
}
