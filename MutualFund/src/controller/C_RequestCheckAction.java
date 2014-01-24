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

			// If no params were passed, return with no errors so that the form
			// will be
			// presented (we assume for the first time).
			if (!form.isPresent()) {
				return "c_requestCheck.jsp";
			}

			// Any validation errors?
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "c_requestCheck.jsp";
			}
//			HttpSession session = request.getSession();
//			CustomerBean c = (CustomerBean) session.getAttribute("customer");

			// Look up the user
			TransactionBean tb = new TransactionBean();
			tb.setTransaction_type("request");
			tb.setAmount(dataConversion.convertFromStringToThreeDigitLong(form
					.getCheckAmt()));
//			tb.setCustomer_id(c.getCustomer_id());
			tb.setCustomer_id(1);
			transactionDAO.createNewTransaction(tb);
			request.setAttribute("message", "the transaction is in process");
			return "c_success.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "c_requestCheck.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "c_requestCheck.jsp";
		}
	}
}
