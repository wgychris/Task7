package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class TransitionForm extends FormBean {
	private String[] fund_id;
	private String[] price;
	
	public String[] getFund_id() {
		return fund_id;
	}

	public void setFund_id(String[] fund_id) {
		this.fund_id = fund_id;
	}

	public String[] getPrice() {
		return price;
	}

	public void setPrice(String[] price) {
		this.price = price;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		return errors;
	}
}