package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

import utils.dataConversion;

public class CreateCustomerForm extends FormBean{
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zip;
	private String cash;
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (userName == null || userName.length() == 0) {
			errors.add("customer name is required");
		}
		if(!cash.matches("-?\\d+(\\.\\d+)?")&&cash.length()!=0){
			errors.add("cash is not a number");
		}else if(!dataConversion.validDoubleNotNegative(cash)){
			errors.add("cash is not a valid number");
		}
		if (firstName == null || firstName.length() == 0) {
			errors.add("firstName is required");
		}
		if (lastName == null || lastName.length() == 0) {
			errors.add("lastName is required");
		}
		if (address1 == null || address1.length() == 0) {
			errors.add("address1 is required");
		}
		if (address2 == null || address2.length() == 0) {
			errors.add("address2 is required");
		}
		if (password == null || password.length() == 0) {
			errors.add("password is required");
		}
		
		if(!password.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$")){
			errors.add("password must contain both character and number, the length is between 6 to 16");
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
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getCash() {
		return cash;
	}
	public void setCash(String cash) {
		this.cash = cash;
	}
	
}
