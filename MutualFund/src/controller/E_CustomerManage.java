package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.genericdao.Transaction;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.CustomerBean;
import formbeans.SearchCustomerName;
import model.CustomerDAO;
import model.FundDAO;
import model.Model;
import model.PositionDAO;
import model.TransactionDAO;

import org.genericdao.*;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class E_CustomerManage extends Action {
	private FormBeanFactory<SearchCustomerName> formBeanFactory = FormBeanFactory
			.getInstance(SearchCustomerName.class);

	// private TransactionDAO transactionDAO;
	// private FundDAO fundDAO;
	// private PositionDAO positionDAO;
	private CustomerDAO customerDAO;

	public E_CustomerManage(Model model) {
		// transactionDAO = model.getTransactionDAO();
		// fundDAO = model.getFundDAO();
		// positionDAO = model.getPositionDAO();
		customerDAO = model.getCustomerDAO();
	}

	@Override
	public String getName() {
		return "e_customermanage.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		try {

			SearchCustomerName form = formBeanFactory.create(request);
			request.setAttribute("form", form);

			// presented (we assume for the first time).
			if (!form.isPresent()) {
				//request.getSession().setAttribute("users", new CustomerBean());
				return "e_customermanage.jsp";
			}
			// check any other errors from JSP
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "e_customermanage.jsp";
			}
			// check if the customer username exist
			// return -1 means this user not existed
			Transaction.begin();
			//int customer_id = customerDAO.getCustomerId(form.getUsername());

			 CustomerBean [] cbs = customerDAO.getRelateCustomers(form.getUsername());
			 System.out.println(cbs.length);
			 for(CustomerBean c:cbs){
				 System.out.println(c.getFirstname());
			 }
			/*if (customer_id == -1) {
				errors.add("Invalid User Name");
				Transaction.commit();
				return "e_customermanage.jsp";
			}
			CustomerBean cb = customerDAO.read(customer_id);*/
			 if(cbs.length==0){
				 errors.add("No related user");
				return "e_customermanage.jsp";
			 }
			/*
			 * send customer bean to jsp as "users" could add other params like
			 * first name, last name
			 */
			request.setAttribute("users", cbs);
			Transaction.commit();
			return "e_customermanage.jsp";
		} catch (RollbackException e) {
			errors.add(e.toString());
			return "e_customermanage.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "e_customermanage.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "e_customermanage.jsp";
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}
}
