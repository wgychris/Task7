package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.CustomerDAO;
import model.Model;
import model.TransactionDAO;

import org.genericdao.RollbackException;
import org.genericdao.Transaction;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import utils.dataConversion;
import databeans.TransactionBean;
import formbeans.DepositCheckForm;

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
public class E_DepositCheckAction extends Action {
	private FormBeanFactory<DepositCheckForm> formBeanFactory = FormBeanFactory
			.getInstance(DepositCheckForm.class);

	private TransactionDAO transactionDAO;
	private CustomerDAO customerDAO;

	public E_DepositCheckAction(Model model) {
		transactionDAO = model.getTransactionDAO();
		customerDAO = model.getCustomerDAO();
	}

	public String getName() {
		return "e_depositCheck.do";
	}

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			DepositCheckForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);

			// If no params were passed, return with no errors so that the form
			// will be
			// presented (we assume for the first time).
			if (!form.isPresent()) {
				return "e_depositCheck.jsp";
			}

			// Any validation errors?
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "e_depositCheck.jsp";
			}

			int customerId = -1;
			Transaction.begin();
			customerId = customerDAO.getCustomerId(form.getCustomer());
			if (customerId == -1) {
				Transaction.commit();
				errors.add("cannot find customerId with customerName");
				return "e_depositCheck.jsp";
			}
			// Look up the user
			TransactionBean tb = new TransactionBean();
			tb.setTransaction_type("deposit");
			tb.setAmount(dataConversion.convertFromStringToThreeDigitLong(form
					.getAmount()));
			tb.setCustomer_id(customerId);
			// Transaction.begin();
			transactionDAO.createNewTransaction(tb);
			request.setAttribute("message", "the transaction is in process");
			Transaction.commit();
			return "e_success.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "e_depositCheck.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "e_depositCheck.jsp";
		} catch (NumberFormatException e) {
			System.out.print("catched");
			errors.add("Input Amount is too large");
			return "e_depositCheck.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "e_depositCheck.jsp";
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}
}
