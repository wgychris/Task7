package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

import utils.dataConversion;

public class CreateFundForm extends FormBean {
	private String fundname;
	private String symbol;

	public String getFundname() {
		return fundname;
	}

	public void setFundname(String fundname) {
		this.fundname = trimAndConvert(fundname, "<>\"");
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = trimAndConvert(symbol, "<>\"");
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (fundname == null || fundname.length() == 0) {
			errors.add("Fund name is required");
		}
		if (symbol == null || symbol.length() == 0) {
			errors.add("Ticker name is required");
		}
		
		if (symbol == null || symbol.length() > 5) {
			errors.add("Length of Ticker should not be greater than 5");
		}
		
		if (fundname.matches(".*[<>\"].*") ) {
			errors.add("Fund name may not contain angle brackets or quotes");
		}
		if (symbol.matches(".*[<>\"].*") ) {
			errors.add("Ticker name may not contain angle brackets or quotes");
		}
		if (!dataConversion.validStringLength(fundname)) {
			errors.add("Fund name should be no longer than 30 characters");
		}
		if (!dataConversion.validStringLength(symbol)) {
			errors.add("Ticker name should be no longer than 30 characters");
		}
		return errors;
	}
}