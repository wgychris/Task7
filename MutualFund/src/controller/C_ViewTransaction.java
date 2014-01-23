/**
 * 
 */
package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import databeans.CustomerBean;
import databeans.FundBean;
import databeans.TransactionBean;
import model.FundDAO;
import model.Model;
import model.PositionDAO;
import model.TransactionDAO;

/**
 * @author yusizhang
 *
 */
public class C_ViewTransaction extends Action{



	//formBean needed?
	//some dao to be used
	private TransactionDAO transactionDAO;
	private FundDAO fundDAO;
	private PositionDAO positionDAO;
	
	//ini DAOs
	public C_ViewTransaction(Model model){
		transactionDAO = model.getTransactionDAO();
		fundDAO = model.getFundDAO();
		positionDAO = model.getPositionDAO();
	}
	
	@Override
	public String getName() {
		
		return "viewtransaction.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
		try {
			//customer from session;
			CustomerBean cb = (CustomerBean)request.getSession().getAttribute("customer");
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
