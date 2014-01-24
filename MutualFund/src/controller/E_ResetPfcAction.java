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

public class E_ResetPfcAction extends Action {
	private FormBeanFactory<ChangePwdForm> formBeanFactory = FormBeanFactory.getInstance(ChangePwdForm.class);
	
	private CustomerDAO customerDAO;

	public E_ResetPfcAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	public String getName() { return "e_reset-pfc.do"; }
    
    public String perform(HttpServletRequest request) {
    	// Set up error list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);

        try {
            
	        
	        // Load the form parameters into a form bean
	        ChangePwdForm form = formBeanFactory.create(request);
	        
	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "c_reset-pfc.jsp";
	        }
	
	        // Check for any validation errors
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "c_reset-pfc.jsp";
	        }
	
			CustomerBean customer = (CustomerBean) request.getAttribute("customer");
	
			// Change the password
        	customerDAO.changePassword(customer.getCustomer_id(),form.getNewPassword());
	
			request.setAttribute("message","Password changed for "+customer.getUsername());
	        return "c_success.jsp";
        } catch (RollbackException e) {
        	errors.add(e.toString());
        	return "error.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.toString());
        	return "error.jsp";
        }
    }
}
