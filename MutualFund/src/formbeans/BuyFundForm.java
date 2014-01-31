package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

import utils.dataConversion;

public class BuyFundForm extends FormBean {
	private String fundTicker;
	private String amount;

	public String getFundTicker() {
		return fundTicker;
	}

	public void setFundTicker(String fundTicker) {
		this.fundTicker = trimAndConvert(fundTicker, "<>\"");
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = trimAndConvert(amount, "<>\"");
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		if (fundTicker == null || fundTicker.length() == 0) {
			errors.add("FundTicker is required");
		}
		if (amount == null || amount.length() == 0) {
			errors.add("Amount is required");
		} else if (!amount.matches("-?\\d+(\\.\\d+)?")) {
			errors.add("Invalid amount");
		} else if (!dataConversion.validDoubleMoreThanZero(amount)) {
			errors.add("Invalid amount");
		}
		return errors;
	}
}