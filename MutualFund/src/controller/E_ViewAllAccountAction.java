/**
 * 
 */
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
import formbeans.SearchCustomerName;
import org.genericdao.*;
public class E_ViewAllAccountAction extends Action {
	private FormBeanFactory<SearchCustomerName> formBeanFactory = FormBeanFactory
			.getInstance(SearchCustomerName.class);
	private CustomerDAO customerDAO;

	public E_ViewAllAccountAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	public String getName() {
		return "e_viewAllAccount.do";
	}

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		try {
			SearchCustomerName form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			int customer_id = customerDAO.getCustomerId(form.getUsername());
			CustomerBean cb = customerDAO.getCustomerInfo(customer_id);

			if (!form.isPresent()) {
				return "e_viewAllAccount.jsp";
			}
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "e_viewAllAccount.jsp";
			}
			request.setAttribute("customer", cb);
			HttpSession session = request.getSession();
			session.setAttribute("customer", cb);
			return "e_viewAccount.do";
		} catch (RollbackException e) {
			System.out.print("e1");
			errors.add(e.toString());
			return "error-list.jsp";
		} catch (FormBeanException e) {
			errors.add(e.toString());
			return "error-list.jsp";
		}
	}
}
