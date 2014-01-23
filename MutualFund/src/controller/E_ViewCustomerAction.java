
package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import databeans.CustomerBean;
import model.CustomerDAO;
import model.Model;
import model.TransactionDAO;



public class E_ViewCustomerAction extends Action{
	private CustomerDAO customerDAO;

	
	//ini DAOs
	public E_ViewCustomerAction(Model model){
//		CustomerDAO = model.getCustomerDAO();
//		fundDAO = model.getFundDAO();
//		positionDAO = model.getPositionDAO();
	}
	
	@Override
	public String getName() {
		
		return "e_view-customer.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
		try {
			//customer from session;
			CustomerBean [] customerList = customerDAO.getAllCustomers();
			FundBean [] fbarray = fundDAO.getAllFunds();
			TransactionBean[] tbarray = transactionDAO.getTransactionByCustomerId(cb.getCustomer_id());
			//user is for jsp page, avoid the same name of customer form session
			request.setAttribute("user", cb); //single javabean
			request.setAttribute("funds", fbarray); //array javabean
			request.setAttribute("transactions", tbarray); //array javabean belongs to certain customer
			
			return "c_viewtransaction.jsp";
		} catch (RollbackException e) {
			errors.add(e.toString());
        	return "error.jsp";
		}
        
	}
	
}

