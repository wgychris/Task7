package controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.FundDAO;
import model.Model;
import model.TransactionDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.CustomerBean;
import databeans.EmployeeBean;
import databeans.FundBean;
import databeans.TransactionBean;
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
public class E_TransitionAction extends Action {
	private FormBeanFactory<TransitionForm> formBeanFactory = FormBeanFactory.getInstance(TransitionForm.class);
	
	private FundDAO fundDAO;
	private TransactionDAO transactionDAO;

	public E_TransitionAction(Model model) {
		fundDAO = model.getFundDAO();
		transactionDAO=model.getTransactionDAO();
	}

	public String getName() { return "e_transition.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
	    	TransitionForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);

	        FundBean[] funds = fundDAO.getAllFunds();
	        
	        if (funds.length == 0) {
	            errors.add("No funds created yet");
	            return "e_transitionDay.jsp";
	        }

	        request.setAttribute("funds",funds);
	     // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "e_transitionDay.jsp";
	        }

	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "e_transitionDay.jsp";
	        }
	        
	        String[] price=form.getPrice();
	        String[] fund_id=form.getFund_id();
	        HashMap<String,String> map=new HashMap<String, String>();
	        for(int i=0;i<price.length;i++){
	        	map.put(fund_id[i], price[i]);
	        }
	        TransactionBean[] tbs=transactionDAO.getTransactionByDate(null);
	        for(int i=0;i<tbs.length;i++){
	        	if(tbs[i].getTransaction_type().equals("deposit")){
	        		transactionDAO.updateTransactionDate(tbs[i], form.getTransitionDay());
	        	}else if(tbs[i].getTransaction_type().equals("request")){
	        		transactionDAO.updateTransactionDate(tbs[i], form.getTransitionDay());
	        	}else if(tbs[i].getTransaction_type().equals("buyFund")){
	        		
	        	}else if(tbs[i].getTransaction_type().equals("sellFund")){
	        		
	        	}else{
	        		
	        	}
	        }
	   
	        request.setAttribute("message","the transitionDay is updated successfully");
	        return "e_success.jsp";
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "error-list.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "error-list.jsp";
        }
    }
}
