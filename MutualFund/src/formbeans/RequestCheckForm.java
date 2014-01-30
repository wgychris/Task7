package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

import utils.dataConversion;

public class RequestCheckForm extends FormBean {
	private String checkAmt;
	private String amountId;
	
	public String getCheckAmt() {
		return checkAmt;
	}
	public void setCheckAmt(String checkAmt) {
		this.checkAmt =  trimAndConvert(checkAmt,"<>\"");
	}
	
	public String getamountId() {
		return amountId;
	}
	public void setAmountId(String amountId) {
		this.amountId = trimAndConvert(amountId,"<>\"");
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (checkAmt == null || checkAmt.length() == 0) {
			errors.add("Amount is required");
		}
		if(!checkAmt.matches("-?\\d+(\\.\\d+)?")){
			errors.add("Invalid amount");
		}else if(dataConversion.validDoubleMoreThanZero(checkAmt)){
			errors.add("check amount is not a valid number");
		}
		
		return errors;
	}
}