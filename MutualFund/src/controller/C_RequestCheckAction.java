package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

	public C_RequestCheckAction(Model model) {
		transactionDAO = model.getTransactionDAO();
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
			if (!form.isPresent()) {
				return "c_requestCheck.jsp";
			}

			// Any validation errors?
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "c_requestCheck.jsp";
			}
			long inpuntAmount = dataConversion
					.convertFromStringToTwoDigitLong(form.getCheckAmt());
			HttpSession session = request.getSession();
			CustomerBean c = (CustomerBean) session.getAttribute("customer");
			long tmpCash = c.getTempcash();
			if (inpuntAmount > tmpCash) {
				errors.add("Amount should not be greater than " + tmpCash);
				return "c_requestCheck.jsp";
			}
			TransactionBean tb = new TransactionBean();
			c.setTempcash(tmpCash - inpuntAmount);
			tb.setTransaction_type("request");
			tb.setAmount(inpuntAmount);
			tb.setCustomer_id(1);

			Transaction.begin();

			transactionDAO.createNewTransaction(tb);
			request.setAttribute("message", "the transaction is in process");
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
			return "e_transitionDay.jsp.jsp";
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}
}
