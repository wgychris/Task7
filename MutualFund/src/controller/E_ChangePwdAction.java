package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Model;
import model.EmployeeDAO;

import org.genericdao.RollbackException;
import org.genericdao.Transaction;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.EmployeeBean;
import formbeans.ChangePwdForm;

import org.genericdao.*;

public class E_ChangePwdAction extends Action {
	private FormBeanFactory<ChangePwdForm> formBeanFactory = FormBeanFactory
			.getInstance(ChangePwdForm.class);

	private EmployeeDAO employeeDAO;

	public E_ChangePwdAction(Model model) {
		employeeDAO = model.getEmployeeDAO();
	}

	public String getName() {
		return "e_change-pwd.do";
	}

	public String perform(HttpServletRequest request) {
		// Set up error list
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {

			// Load the form parameters into a form bean
			ChangePwdForm form = formBeanFactory.create(request);

			// If no params were passed, return with no errors so that the form
			// will be
			// presented (we assume for the first time).
			if (!form.isPresent()) {
				return "e_change-pwd.jsp";
			}

			// Check for any validation errors
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "e_change-pwd.jsp";
			}

			EmployeeBean employee = (EmployeeBean) request.getSession()
					.getAttribute("employee");

			// Change the password
			Transaction.begin();
			employeeDAO.changePassword(employee.getUsername(),
					form.getNewPassword());

			request.setAttribute("message",
					"Password changed for " + employee.getUsername());
			Transaction.commit();
			return "e_success.jsp";
		} catch (RollbackException e) {
			errors.add(e.toString());
			return "error.jsp";
		} catch (FormBeanException e) {
			errors.add(e.toString());
			return "error.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "e_transitionDay.jsp.jsp";
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}
}
