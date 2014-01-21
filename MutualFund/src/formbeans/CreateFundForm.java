package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class CreateFundForm extends FormBean {
	private String fundname;
	private String symbol;
	
	public String getFundname() {
		return fundname;
	}

	public void setFundname(String fundname) {
		this.fundname = trimAndConvert(fundname,"<>\"");
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = trimAndConvert(symbol,"<>\"");
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (fundname == null || fundname.length() == 0) {
			errors.add("Account is required");
		}
		if (symbol == null || symbol.length() == 0) {
			errors.add("customer is required");
		}
		
		return errors;
	}
}