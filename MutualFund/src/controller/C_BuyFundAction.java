package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.CustomerDAO;
import model.FundDAO;
import model.Model;
import model.TransactionDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import utils.dataConversion;
import databeans.CustomerBean;
import databeans.FundBean;
import databeans.TransactionBean;
import formbeans.BuyFundForm;

import org.genericdao.*;

/*
 * Processes the parameters from the form in login.jsp.
 * If successful, set the "user" session attribute to the
 * user's User bean and then redirects to view the originally
 * requested photo.  If there was no photo originally requested
 * to be viewed (as specified by the "redirect" hidden form
 * value), just redirect to manage.do to allow the user to manage
 * his photos.
 */
public class C_BuyFundAction extends Action {
	private FormBeanFactory<BuyFundForm> formBeanFactory = FormBeanFactory
			.getInstance(BuyFundForm.class);
	private TransactionDAO transactionDAO;
	private FundDAO fundDAO;
	private CustomerDAO customerDAO;

	public C_BuyFundAction(Model model) {
		transactionDAO = model.getTransactionDAO();
		fundDAO = model.getFundDAO();
		customerDAO = model.getCustomerDAO();
	}

	public String getName() {
		return "c_buyFund.do";
	}

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			
			BuyFundForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);

			// If no params were passed, return with no errors so that the form
			// will be
			// presented (we assume for the first time).
			Transaction.begin();
			HttpSession session = request.getSession();
			CustomerBean customer = (CustomerBean) session
					.getAttribute("customer");
			int id=customer.getCustomer_id();
			CustomerBean cb=customerDAO.getCustomerInfo(id);
			session.setAttribute("customer", cb);
			if (!form.isPresent()) {
				Transaction.commit();
				return "c_buyFund.jsp";
			}

			// Any validation errors?
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				Transaction.commit();
				return "c_buyFund.jsp";
			}
			
			
			if (!fundDAO.checkFundByTicker(form.getFundTicker())) {
				errors.add("Invalid ticker");
				Transaction.commit();
				return "c_buyFund.jsp";
			}

			// long inputAmount = dataConversion
			// .convertFromStringToThreeDigitLong(form.getAmount());
			long inputAmount = dataConversion
					.convertFromStringToTwoDigitLong(form.getAmount());
			if (inputAmount > cb.getTempcash()) {
				errors.add("Amount should not be greater than available cash");
				Transaction.commit();
				return "c_buyFund.jsp";

			}

			FundBean fundBean = (FundBean) fundDAO.getFundByTicker(form
					.getFundTicker());
			int fund_id = fundBean.getFund_id();
			TransactionBean t = new TransactionBean();
			t.setFund_id(fundBean.getFund_id());
			t.setAmount(inputAmount);
			t.setCustomer_id(customer.getCustomer_id());
			t.setFund_id(fund_id);
			t.setTransaction_type("buy");
			customer.setTempcash(customer.getTempcash() - inputAmount);
//			 customerDAO.updataTempCash(customer.getCustomer_id(),
//			 customer.getTempcash() - inputAmount);
			customerDAO.update(customer);
			transactionDAO.create(t);

			request.setAttribute("message", "new fund has been bought");
			session.setAttribute("customer", cb);
			Transaction.commit();
			
			return "c_success.jsp";

		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "c_buyFund.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "c_buyFund.jsp";
		} catch (NumberFormatException e) {
			System.out.print("catched");
			errors.add("Input Amount is too large");
			return "c_buyFund.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "c_buyFund.jsp";
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}
}
