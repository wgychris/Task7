package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.CustomerDAO;
import model.Model;
import model.TransactionDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import utils.dataConversion;
import databeans.CustomerBean;
import databeans.TransactionBean;
import formbeans.RequestCheckForm;

import org.genericdao.*;

public class C_RequestCheckAction extends Action {
	private FormBeanFactory<RequestCheckForm> formBeanFactory = FormBeanFactory
			.getInstance(RequestCheckForm.class);

	private TransactionDAO transactionDAO;
	private CustomerDAO customerDAO;

	public C_RequestCheckAction(Model model) {
		transactionDAO = model.getTransactionDAO();
		customerDAO=model.getCustomerDAO();
	}

	public String getName() {
		return "c_requestCheck.do";
	}

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			RequestCheckForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			Transaction.begin();
			HttpSession session = request.getSession();
			CustomerBean cb = (CustomerBean) session.getAttribute("customer");
			int id=cb.getCustomer_id();
			CustomerBean c=customerDAO.getCustomerInfo(id);
			session.setAttribute("customer", c);
			if (!form.isPresent()) {
				Transaction.commit();
				return "c_requestCheck.jsp";
			}

			// Any validation errors?
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				Transaction.commit();
				return "c_requestCheck.jsp";
			}
			long inpuntAmount = dataConversion
					.convertFromStringToTwoDigitLong(form.getCheckAmt());
			long tmpCash = c.getTempcash();
			if (inpuntAmount > tmpCash) {
				errors.add("Amount should not be greater than " + tmpCash);
				Transaction.commit();
				return "c_requestCheck.jsp";
			}
			TransactionBean tb = new TransactionBean();
			c.setTempcash(tmpCash - inpuntAmount);
			tb.setTransaction_type("request");
			tb.setAmount(inpuntAmount);
			tb.setCustomer_id(1);
			transactionDAO.createNewTransaction(tb);
			request.setAttribute("message", "the transaction is in process");
			session.setAttribute("customer", c);
			Transaction.commit();
			return "c_success.jsp";

		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "c_requestCheck.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "c_requestCheck.jsp";
		} catch (NumberFormatException e) {
			// System.out.print("catched");
			errors.add("Input Amount is too large");
			return "c_requestCheck.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "c_requestCheck.jsp";
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}
}
