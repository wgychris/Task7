package controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.EmployeeDAO;
import model.Model;

import org.genericdao.RollbackException;
import org.genericdao.Transaction;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.EmployeeBean;
import formbeans.CreateEmployeeForm;

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
public class E_CreateEmployeeAction extends Action {
	private FormBeanFactory<CreateEmployeeForm> formBeanFactory = FormBeanFactory.getInstance(CreateEmployeeForm.class);
	
	private EmployeeDAO employeeDAO;

	public E_CreateEmployeeAction(Model model) {
		employeeDAO = model.getEmployeeDAO();
	}

	public String getName() { return "e_create_employee.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
	    	CreateEmployeeForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);

	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "e_create_employee.jsp";
	        }

	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "e_create_employee.jsp";
	        }

	        // Look up the user
	        EmployeeBean cb=new EmployeeBean();
	        cb.setUsername(form.getUserName());
	        cb.setFirstname(form.getFirstName());
	        cb.setLastname(form.getLastName());
	        cb.setPassword(form.getPassword());
	        Transaction.begin();
	        employeeDAO.create(cb);
	        request.setAttribute("message","new employee has been added");
	        Transaction.commit();
	        return "e_success.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "error-list.jsp";
        } catch (RollbackException e) {
        	return "error-list.jsp";
		}
        finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
    }
}
