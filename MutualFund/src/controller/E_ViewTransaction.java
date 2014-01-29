/**
 * Employee can view transaction history by a given customer id(username)
 */
package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.genericdao.Transaction;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.CustomerBean;
import databeans.TempTransaction;
import databeans.TransactionBean;
import formbeans.SearchCustomerName;
import model.CustomerDAO;
import model.FundDAO;
import model.Model;
import model.PositionDAO;
import model.TransactionDAO;

import org.genericdao.*;
/**
 * @author yusizhang
 *Jan 23 Version 1.0
 */
public class E_ViewTransaction extends Action{
	private FormBeanFactory<SearchCustomerName> formBeanFactory = FormBeanFactory
			.getInstance(SearchCustomerName.class);
	
	private TransactionDAO transactionDAO;
	private FundDAO fundDAO;
	private PositionDAO positionDAO;
	private CustomerDAO customerDAO;

	//ini DAOs
	public E_ViewTransaction(Model model) {
		transactionDAO = model.getTransactionDAO();
		fundDAO = model.getFundDAO();
		positionDAO = model.getPositionDAO();
		customerDAO = model.getCustomerDAO();
	}
	@Override
	public String getName() {
		
		return "e_viewTransactionHistory.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try{
        	SearchCustomerName form = formBeanFactory.create(request);
        	request.setAttribute("form", form);
        	
        	// presented (we assume for the first time).
        	if (!form.isPresent()) {
				return "e_searchtoviewtrans.jsp";
			}
        	//check any other errors from JSP
        	errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "e_searchtoviewtrans.jsp";
			}
			//check if the customer username exist
			//return -1 means this user not existed
			Transaction.begin();
			int customer_id = customerDAO.getCustomerId(form.getUsername());
			CustomerBean cb = customerDAO.read(customer_id);
			if(customer_id==-1){
				errors.add("Invalid User Name");
				Transaction.commit();
				return "e_searchtoviewtrans.jsp";
			}
			TransactionBean[] tbarray = transactionDAO.getTransactionByCustomerId(customer_id);
//			TransactionBean[] tbarray = transactionDAO.getTransactionByCustomerId(1); //test user id is 1
       
        
			int length = transactionDAO.getTransactionByCustomerId(cb.getCustomer_id()).length;
			//ERROR if the transaction is empty!!!!
//			int length = transactionDAO.getTransactionByCustomerId(1).length;
			
			ArrayList<TempTransaction> al = new ArrayList<TempTransaction>();
			TransactionBean tb = new TransactionBean();
			
			for(int i=0;i<length;i++){
				TempTransaction tt = new TempTransaction(); //need to create new object for each loop!!!
				int fundid = transactionDAO.getTransactionByCustomerId(customer_id)[i].getFund_id();
				//These are for testing
//				tt.setAmount(transactionDAO.getTransactionByCustomerId(1)[i].getAmount()/100);
//				tt.setShares(transactionDAO.getTransactionByCustomerId(1)[i].getShares()/1000);
//				tt.setCustomer_id(transactionDAO.getTransactionByCustomerId(1)[i].getCustomer_id());
//				tt.setExecute_date((transactionDAO.getTransactionByCustomerId(1)[i].getExecute_date()));
//				tt.setFund_id((transactionDAO.getTransactionByCustomerId(1)[i].getFund_id()));
//				tt.setTransaction_id(transactionDAO.getTransactionByCustomerId(1)[i].getTransaction_id());
//				tt.setTransaction_type((transactionDAO.getTransactionByCustomerId(1)[i].getTransaction_type()));
//				tt.setName(fundDAO.getFundByFundId(fundid).getName());
//				tt.setSymbol(fundDAO.getFundByFundId(fundid).getSymbol());
				
				tt.setAmount(transactionDAO.getTransactionByCustomerId(customer_id)[i].getAmount()/100);
				tt.setShares(transactionDAO.getTransactionByCustomerId(customer_id)[i].getShares()/1000);
				tt.setCustomer_id(transactionDAO.getTransactionByCustomerId(customer_id)[i].getCustomer_id());
				if((transactionDAO.getTransactionByCustomerId(customer_id)[i].getExecute_date()==null)){
					tt.setExecute_date("Pending");
				}
				else{
				tt.setExecute_date((transactionDAO.getTransactionByCustomerId(customer_id)[i].getExecute_date()));
				}
				tt.setFund_id((transactionDAO.getTransactionByCustomerId(customer_id)[i].getFund_id()));
				tt.setTransaction_id(transactionDAO.getTransactionByCustomerId(customer_id)[i].getTransaction_id());
				tt.setTransaction_type((transactionDAO.getTransactionByCustomerId(customer_id)[i].getTransaction_type()));
				if(tt.getFund_id()==0){
					al.add(tt);
					continue;
				}
				tt.setName(fundDAO.getFundByFundId(fundid).getName());
				tt.setSymbol(fundDAO.getFundByFundId(fundid).getSymbol());
				al.add(tt);
//				System.out.println("al "+i+" "+al.get(i).getTransaction_id());
			}
			
			request.setAttribute("temptransactions", al); //send arraylist of beans to the jsp
			request.setAttribute("searcheduser", cb);
			Transaction.commit();
			return "e_viewTransactionHistory.jsp";
        
        }catch (RollbackException e) {
			errors.add(e.toString());
        	return "error.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "error-list.jsp";
		}
        finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
        
        
        
        
	}

}
