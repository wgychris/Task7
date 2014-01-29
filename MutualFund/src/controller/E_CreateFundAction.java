package controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.FundDAO;
import model.Model;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.FundBean;
import formbeans.CreateFundForm;
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
public class E_CreateFundAction extends Action {
	private FormBeanFactory<CreateFundForm> formBeanFactory = FormBeanFactory.getInstance(CreateFundForm.class);
	
	private FundDAO fundDAO;

	public E_CreateFundAction(Model model) {
		fundDAO=model.getFundDAO();
	}

	public String getName() { return "e_createFund.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
	    	CreateFundForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);

	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "e_createFund.jsp";
	        }

	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "e_createFund.jsp";
	        }

	        // Look up the user
	        FundBean fb=new FundBean();
	        fb.setName(form.getFundname());
	        fb.setSymbol(form.getSymbol());
	        fundDAO.createAutoIncrement(fb);
	        request.setAttribute("message","the fund has been created");
	        return "e_success.jsp";
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "e_createFund.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "e_createFund.jsp";
        }
    }
}
