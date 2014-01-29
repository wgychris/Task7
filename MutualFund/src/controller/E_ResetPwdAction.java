package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.CustomerDAO;
import model.Model;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.CustomerBean;

//import databeans.User;
import formbeans.ChangePwdForm;
import formbeans.CreateCustomerForm;
import formbeans.SearchCustomerName;
import org.genericdao.*;
public class E_ResetPwdAction extends Action {
	private FormBeanFactory<SearchCustomerName> formBeanFactory = FormBeanFactory
			.getInstance(SearchCustomerName.class);

	private CustomerDAO customerDAO;

	public E_ResetPwdAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	public String getName() {
		return "e_reset-pwd.do";
	}

	public String perform(HttpServletRequest request) {
		// Set up error list
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {

			// Load the form parameters into a form bean
			SearchCustomerName form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			int customer_id = customerDAO.getCustomerId(form.getUsername());
			CustomerBean cb = customerDAO.getCustomerInfo(customer_id);
			// If no params were passed, return with no errors so that the form
			// will be

			// presented (we assume for the first time).
			if (!form.isPresent()) {
				return "e_reset-pwd.jsp";
			}

			// Check for any validation errors
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "e_reset-pwd.jsp";
			}
			CustomerBean customer = (CustomerBean) request
					.getAttribute("userName");
			/*
			 * CustomerBean[] userName = customerDAO.getAllCustomers(); if
			 * (userName == null || userName.length() == 0) {
			 * errors.add("User must be specified"); return "error.jsp";
			 * //return -1 means this user not existed
			 * 
			 * if(customer_id==-1){ errors.add("Invalid User Name"); return
			 * "e_reset-pwd.jsp"; }
			 * 
			 * CustomerBean customer = customerDAO.read(userName); if (customer
			 * == null) { errors.add("Invalid User: "+userName); return
			 * "error.jsp"; }
			 */

			// // Change the password
			// customerDAO.changePassword(customer.getCustomer_id(),form.getNewPassword());

			request.setAttribute("customer", cb);
			HttpSession session = request.getSession();
			session.setAttribute("customer", cb);
			return "e_reset-pfc.do";
		} catch (RollbackException e) {
			errors.add(e.toString());
			return "error.jsp";
		} catch (FormBeanException e) {
			errors.add(e.toString());
			return "error.jsp";
		}
	}
}
