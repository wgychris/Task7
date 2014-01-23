package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class CreateEmployeeForm extends FormBean{
	private String userName;
	private String password;
	private String firstName;
	private String lastName;

	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (userName == null || userName.length() == 0) {
			errors.add("customer name is required");
		}
		
		if (firstName == null || firstName.length() == 0) {
			errors.add("firstName is required");
		}
		if (lastName == null || lastName.length() == 0) {
			errors.add("lastName is required");
		}
		
		if (password == null || password.length() == 0) {
			errors.add("password is required");
		}
		return errors;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	
}
