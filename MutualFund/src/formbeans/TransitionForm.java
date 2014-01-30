package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

import utils.dataConversion;

public class TransitionForm extends FormBean {
	private String[] fund_id;
	private String[] price;
	private String transitionDay;
	
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

	
	public String getTransitionDay() {
		return transitionDay;
	}

	public void setTransitionDay(String transitionDay) {
		this.transitionDay = transitionDay;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		if (!dataConversion.isDate(transitionDay)) {
			errors.add("TransitionDay is not valid");
		}
		for(String s:price){
			if (s == null || s.length() == 0) {
				errors.add("Prices for all funds are required");
			}
			if(!dataConversion.validDoubleMoreThanZero(s)){
				errors.add("Prices are not valid");
				break;
			}
		}
		if(fund_id.length!=price.length){
			errors.add("You have to enter price for each fund");
		}
		return errors;
	}
}