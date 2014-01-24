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
import databeans.TempTransaction;
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
			int customer_id = cb.getCustomer_id();
			
//			System.out.println(cb.getUsername());
			
//			TransactionBean[] tbarray = transactionDAO.getTransactionByCustomerId(cb.getCustomer_id());
			TransactionBean[] tbarray = transactionDAO.getTransactionByCustomerId(1); //test user id is 1
			
//			System.out.println(tbarray[0].getTransaction_id());
			
//			int length = transactionDAO.getTransactionByCustomerId(cb.getCustomer_id()).length;
			int length = transactionDAO.getTransactionByCustomerId(1).length;
//			System.out.println("length is "+length);
			ArrayList<TempTransaction> al = new ArrayList<TempTransaction>();
			TransactionBean tb = new TransactionBean();
			
			for(int i=0;i<length;i++){
				TempTransaction tt = new TempTransaction(); //need to create new object for each loop!!!
				int fundid = transactionDAO.getTransactionByCustomerId(1)[i].getFund_id();
//				System.out.println("fundid is" + fundid);
				
				
				/*These are for testing
				tt.setAmount(transactionDAO.getTransactionByCustomerId(1)[i].getAmount()/100);
				tt.setShares(transactionDAO.getTransactionByCustomerId(1)[i].getShares()/1000);
				
				tt.setCustomer_id(transactionDAO.getTransactionByCustomerId(1)[i].getCustomer_id());
				tt.setExecute_date((transactionDAO.getTransactionByCustomerId(1)[i].getExecute_date()));
				tt.setFund_id((transactionDAO.getTransactionByCustomerId(1)[i].getFund_id()));
				tt.setTransaction_id(transactionDAO.getTransactionByCustomerId(1)[i].getTransaction_id());
//				System.out.println("transaction id is "+transactionDAO.getTransactionByCustomerId(1)[i].getTransaction_id());
				tt.setTransaction_type((transactionDAO.getTransactionByCustomerId(1)[i].getTransaction_type()));
				
				tt.setName(fundDAO.getFundByFundId(fundid).getName());
				tt.setSymbol(fundDAO.getFundByFundId(fundid).getSymbol());
				*/
				tt.setAmount(transactionDAO.getTransactionByCustomerId(customer_id)[i].getAmount()/100);
				tt.setShares(transactionDAO.getTransactionByCustomerId(customer_id)[i].getShares()/1000);
				tt.setCustomer_id(transactionDAO.getTransactionByCustomerId(customer_id)[i].getCustomer_id());
				tt.setExecute_date((transactionDAO.getTransactionByCustomerId(customer_id)[i].getExecute_date()));
				tt.setFund_id((transactionDAO.getTransactionByCustomerId(customer_id)[i].getFund_id()));
				tt.setTransaction_id(transactionDAO.getTransactionByCustomerId(customer_id)[i].getTransaction_id());
				tt.setTransaction_type((transactionDAO.getTransactionByCustomerId(customer_id)[i].getTransaction_type()));
				tt.setName(fundDAO.getFundByFundId(fundid).getName());
				tt.setSymbol(fundDAO.getFundByFundId(fundid).getSymbol());
				
				al.add(tt);
//				System.out.println("al "+i+" "+al.get(i).getTransaction_id());
			}
			
			
			request.setAttribute("temptransactions", al); //send arraylist of beans to the jsp
			
			System.out.println(al.get(2).getTransaction_id());
			return "c_viewTransactionHistory.jsp";
		} catch (RollbackException e) {
			errors.add(e.toString());
        	return "error.jsp";
		}
        
	}
	
}
