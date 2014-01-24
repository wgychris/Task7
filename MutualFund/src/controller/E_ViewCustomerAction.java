package controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.CustomerDAO;
import model.FundDAO;
import model.Model;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.CustomerBean;
import databeans.EmployeeBean;
import databeans.FundBean;
import formbeans.CreateCustomerForm;
import formbeans.LoginForm;
import formbeans.TransitionForm;

/*
 * Processes the parameters from the form in login.jsp.
 * If successful, set the "user" session attribute to the
 * user's User bean and then redirects to view the originally
 * requested photo.  If there was no photo originally requested
 * to be viewed (as specified by the "redirect" hidden form
 * value), just redirect to manage.do to allow the user to manage
 * his photos.
 */
public class E_ViewCustomerAction extends Action {
	private FormBeanFactory<CreateCustomerForm> formBeanFactory = FormBeanFactory.getInstance(CreateCustomerForm.class);
	
	private CustomerDAO customerDAO;

	public E_ViewCustomerAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	public String getName() { return "e_view-customer.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
	    	CreateCustomerForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);

	        CustomerBean[] customer = customerDAO.getAllCustomers();
	        
	        if (customer.length == 0) {
	            errors.add("No customers created yet");
	            return "e_view-customer.jsp";
	        }

	        
	     // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "e_view-customer.jsp";
	        }

	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "e_view-customer.jsp";
	        }

	       

	        return "e_view-customer.jsp";
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "error-list.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "error-list.jsp";
        }
    }
}
