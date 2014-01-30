package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.CustomerDAO;
import model.Model;

import org.genericdao.RollbackException;
import org.genericdao.Transaction;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.CustomerBean;
import formbeans.LoginForm;

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
public class C_LoginAction extends Action {
	private FormBeanFactory<LoginForm> formBeanFactory = FormBeanFactory
			.getInstance(LoginForm.class);

	private CustomerDAO customerDAO;

	public C_LoginAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	public String getName() {
		return "c_login.do";
	}

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			LoginForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);

			// If no params were passed, return with no errors so that the form
			// will be
			// presented (we assume for the first time).
			if (!form.isPresent()) {
				return "c_login.jsp";
			}

			// Any validation errors?
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "c_login.jsp";
			}

			Transaction.begin();
			// Look up the user
			CustomerBean customer = customerDAO.login(form.getUserName(),
					form.getPassword());

			if (customer == null) {
				errors.add("User Name and Password is not matched!!");
				Transaction.commit();
				return "c_login.jsp";
			}

			// Check the password
			// if (!customer.checkPassword(form.getPassword())) {
			// errors.add("Incorrect password");
			// return "c_login.jsp";
			// }

			// Attach (this copy of) the user bean to the session
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(600);
			session.setAttribute("customer", customer);
			Transaction.commit();
			return "c_viewAccount.do";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "c_login.jsp";
		} catch (RollbackException e) {
			return "c_login.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "c_login.jsp";
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}
}
