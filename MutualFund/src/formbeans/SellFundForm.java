package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class SellFundForm extends FormBean {
	private String fundTicker;
	private String share;

	public String getFundTicker() {
		return fundTicker;
	}

	public void setFundTicker(String fundTicker) {
		this.fundTicker = trimAndConvert(fundTicker, "<>\"");
	}

	public String getShare() {
		return share;
	}

	public void setShare(String share) {
		this.share = trimAndConvert(share, "<>\"");
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		if (fundTicker == null || fundTicker.length() == 0) {
			errors.add("FundTicker is required");
		}
		if (share == null || share.length() == 0) {
			errors.add("Number of shares is required");
		} else {
			int countPoint = 0;
			for (int i = 0; i < share.length(); i++) {
				if (share.charAt(i) == '.'
						&& (++countPoint > 1 || i < share.length() - 4)) {
					errors.add("Invalid number");
					break;
				} else if (share.charAt(i) < '0' || share.charAt(i) > '9') {
					errors.add("Invalid number");
					break;
				}
			}
		}
		return errors;
	}
}