package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class DepositCheckForm extends FormBean {
	private String customer;
	private String amount;
	
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer =  trimAndConvert(customer,"<>\"");
	}
	
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = trimAndConvert(amount,"<>\"");
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (amount == null || amount.length() == 0) {
			errors.add("Account is required");
		}
		if(!amount.matches("-?\\d+(\\.\\d+)?")){
			errors.add("amount is not a number");
		}
		if (customer == null || customer.length() == 0) {
			errors.add("customer is required");
		}
		
		return errors;
	}
}