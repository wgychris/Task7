package databeans;

import org.genericdao.PrimaryKey;

/*
 * EmployeeBean 
 * Yusi Jan 19 Version
 * co-author:
 */
@PrimaryKey("employee_id")
public class EmployeeBean {
	private int employee_id;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getFirstname() {
		return firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public boolean checkPassword(String password2) {
		return password.equals(password2);
	}
	
	
}
