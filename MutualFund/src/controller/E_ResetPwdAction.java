package controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.CustomerDAO;
import model.Model;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.CustomerBean;
import formbeans.ChangePwdForm;
import formbeans.CreateCustomerForm;

public class E_ResetPwdAction extends Action {
	private FormBeanFactory<CreateCustomerForm> formBeanFactory = FormBeanFactory.getInstance(CreateCustomerForm.class);
	
	private CustomerDAO customerDAO;

	public E_ResetPwdAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	public String getName() { return "e_reset-pwd.do"; }
    
    public String perform(HttpServletRequest request) {
    	// Set up error list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);

        try {
            
	        
	        // Load the form parameters into a form bean
        	CreateCustomerForm form = formBeanFactory.create(request);
	        
	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "e_reset-pwd.jsp";
	        }
	
	        // Check for any validation errors
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "e_reset-pwd.jsp";
	        }
	        CustomerBean customer = (CustomerBean) request.getAttribute("userName");
	        /*CustomerBean[] userName = customerDAO.getAllCustomers();
			if (userName == null || userName.length() == 0) {
				errors.add("User must be specified");
				return "error.jsp";
			}
	
			CustomerBean customer = customerDAO.read(userName);
        	if (customer == null) {
    			errors.add("Invalid User: "+userName);
    			return "error.jsp";
    		}*/

	
//			// Change the password
//        	customerDAO.changePassword(customer.getCustomer_id(),form.getNewPassword());
	
			request.setAttribute("userName",customer.getUsername());
	        return "e_reset-pwd.jsp";
//        } catch (RollbackException e) {
//        	errors.add(e.toString());
//        	return "error.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.toString());
        	return "error.jsp";
        }
    }
}
