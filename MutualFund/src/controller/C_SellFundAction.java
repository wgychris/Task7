package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.FundDAO;
import model.Model;
import model.PositionDAO;
import model.TransactionDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import utils.dataConversion;
import databeans.CustomerBean;
import databeans.FundBean;
import databeans.PositionBean;
import databeans.TransactionBean;
import formbeans.SellFundForm;

public class C_SellFundAction extends Action {
	private FormBeanFactory<SellFundForm> formBeanFactory = FormBeanFactory
			.getInstance(SellFundForm.class);

	private TransactionDAO transactionDAO;
	private FundDAO fundDAO;
	private PositionDAO positionDAO;

	public C_SellFundAction(Model model) {
		transactionDAO = model.getTransactionDAO();
		fundDAO = model.getFundDAO();
		positionDAO = model.getPositionDAO();
	}

	public String getName() {
		return "c_sellFund.do";// ??
	}

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			SellFundForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);

			// If no params were passed, return with no errors so that the form
			// will be
			// presented (we assume for the first time).
			if (!form.isPresent()) {
				return "c_sellFund.jsp";
			}

			// Any validation errors?
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "c_sellFund.jsp";
			}
			if (!fundDAO.checkFundByTicker(form.getFundTicker())) {
				errors.add("No such fund exists");
				return "c_sellFund.jsp";
			}
			HttpSession session = request.getSession();
			CustomerBean c = (CustomerBean) session.getAttribute("customer");
			FundBean fundBean = (FundBean) fundDAO.getFundByTicker(form
					.getFundTicker());
			System.out.println("fund id " + fundBean.getFund_id());
			PositionBean positionBean = (PositionBean) positionDAO.getPosition(c.getCustomer_id(), fundBean.getFund_id());
			long tmpShares = positionBean.getTempshares();
			// long maxShares = 10000;
			long inputShares = dataConversion
					.convertFromStringToThreeDigitLong(form.getShare());
			if (inputShares > tmpShares) {
				errors.add("Number of shares should not be greater than "
						+ tmpShares);
				return "c_sellFund.jsp";
			}
			TransactionBean t = new TransactionBean();
			t.setShares(inputShares);
			t.setCustomer_id(c.getCustomer_id());
			// t.setCustomer_id(1);//test
			t.setTransaction_type("sell");
			t.setFund_id(fundBean.getFund_id());
			transactionDAO.createAutoIncrement(t);
			PositionBean p = new PositionBean();
			p.setCustomer_id(c.getCustomer_id());
			// p.setCustomer_id(1);//test
			p.setFund_id(fundBean.getFund_id());
			p.setShares(tmpShares - inputShares);// should use ;
//			positionDAO.create(p);
			positionDAO.updataTempCash(p);

			request.setAttribute("message", "fund has been sold");
			return "c_success.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "error-list.jsp";
		} catch (RollbackException e) {
			return "error-list.jsp";
		}
	}
}
