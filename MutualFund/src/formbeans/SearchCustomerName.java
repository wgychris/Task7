/**
 * Employee input customer id to view his/her transaction history
 */
package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

/**
 * @author yusizhang
 *
 */
public class SearchCustomerName extends FormBean{
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public List<String> getValidationErrors(){
		List<String> errors = new ArrayList<String>();
		if(username==null || username.length()==0){
			errors.add("User Name is required");
		}
		return errors;
	}
}
