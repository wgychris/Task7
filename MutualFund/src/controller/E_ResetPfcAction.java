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
        /*
         * the first time visit this page, we get parameter from JSP form post method.
         * However, when we detetive we need to show the form of this page. we have to redirect the page to e_reset-pfc.jsp again
         * by then, the parameter "username" is lost. 
         * so what we do is to store the value into session.
         * next time we can get the value from session.
         */
        request.setAttribute("errors",errors);
        String username = (String)request.getParameter("username");
        System.out.println(username);
        
        try {
            
        	 
	        // Load the form parameters into a form bean
	        ChangePwdForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);
	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	        	System.out.println("session:"+username);
	        	request.getSession().setAttribute("user", username);
	            return "e_reset-pfc.jsp";
	        }
	        
	        // Check for any validation errors
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	        	request.getSession().setAttribute("user", username);
	            return "e_reset-pfc.jsp";
	        }
	        
	        /*
	         * the process goes to here. 
	         * it means the previous condition is fullfilled
	         * which means we have "user" session already. then we get it.
	         */
//			CustomerBean customer = (CustomerBean) request.getAttribute("customer");
	        username = (String) request.getSession().getAttribute("user");
	       
	        
	        CustomerBean customer = customerDAO.getCustomerInfo(customerDAO.getCustomerId(username));
	        System.out.println(customer.toString());
			// Change the password
        	customerDAO.changePassword(customer.getCustomer_id(),form.getNewPassword());
	
			request.setAttribute("message","Password changed for "+customer.getUsername());
	        return "c_success_forE.jsp";
        } catch (RollbackException e) {
        	errors.add(e.toString());
        	return "error-list.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.toString());
        	return "error-list.jsp";
        }
    }
}
