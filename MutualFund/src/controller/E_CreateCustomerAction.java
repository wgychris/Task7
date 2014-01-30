package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.CustomerDAO;
import model.Model;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import utils.dataConversion;
import databeans.CustomerBean;
import formbeans.CreateCustomerForm;

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
public class E_CreateCustomerAction extends Action {
	private FormBeanFactory<CreateCustomerForm> formBeanFactory = FormBeanFactory
			.getInstance(CreateCustomerForm.class);

	private CustomerDAO customerDAO;

	public E_CreateCustomerAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	public String getName() {
		return "e_create_customer.do";
	}

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			CreateCustomerForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);

			// If no params were passed, return with no errors so that the form
			// will be
			// presented (we assume for the first time).
			if (!form.isPresent()) {
				return "e_create_customer.jsp";
			}

			// Any validation errors?
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "e_create_customer.jsp";
			}

			Transaction.begin();
			if (customerDAO.getCustomerId(form.getUserName()) != -1) {
				errors.add("UserName is already existed!");
				return "e_create_customer.jsp";
			}
			Transaction.commit();

			// Look up the user
			CustomerBean cb = new CustomerBean();
			cb.setUsername(form.getUserName());
			cb.setAddr1(form.getAddress1());
			cb.setAddr2(form.getAddress2());
			cb.setCash(dataConversion.convertFromStringToThreeDigitLong(form
					.getCash()));
			cb.setCity(form.getCity());
			cb.setFirstname(form.getFirstName());
			cb.setLastname(form.getLastName());
			cb.setPassword(form.getPassword());
			cb.setState(form.getState());
			cb.setTempcash(dataConversion
					.convertFromStringToThreeDigitLong(form.getCash()));
			cb.setZip(form.getZip());
			Transaction.begin();
			customerDAO.createAutoIncrement(cb);
			request.setAttribute("message", "new customer has been added");
			Transaction.commit();
			return "e_success.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "error-list.jsp";
		} catch (RollbackException e) {
			return "error-list.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "e_transitionDay.jsp.jsp";
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}
}
