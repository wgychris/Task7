/**
 * 
 */
package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import databeans.CustomerBean;
import databeans.TempTransaction;
import databeans.TransactionBean;
import model.FundDAO;
import model.Model;
import model.TransactionDAO;

import org.genericdao.*;

/**
 * @author yusizhang
 * 
 */
public class C_ViewTransaction extends Action {

	// formBean needed?
	// some dao to be used
	private TransactionDAO transactionDAO;
	private FundDAO fundDAO;

	// ini DAOs
	public C_ViewTransaction(Model model) {
		transactionDAO = model.getTransactionDAO();
		fundDAO = model.getFundDAO();
	}

	@Override
	public String getName() {

		return "c_viewTransactionHistory.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		try {

			// customer from session;
			CustomerBean cb = (CustomerBean) request.getSession().getAttribute(
					"customer");
			int customer_id = cb.getCustomer_id();

			// System.out.println(cb.getUsername());
			Transaction.begin();
			TransactionBean[] tbarray = transactionDAO
					.getTransactionByCustomerId(customer_id);

			int length = 0;
			if (tbarray != null) {
				length = tbarray.length;
			}
			// int length = transactionDAO.getTransactionByCustomerId(1).length;
			System.out.println("length is " + length);
			ArrayList<TempTransaction> al = new ArrayList<TempTransaction>();
			System.out.print("1");
			for (int i = 0; i < length; i++) {
				TempTransaction tt = new TempTransaction();
				int fundid = tbarray[i].getFund_id();
				tt.setAmount(tbarray[i].getAmount());
				tt.setCustomer_id(tbarray[i].getCustomer_id());
				tt.setShares(tbarray[i].getShares());
				if ((tbarray[i].getExecute_date()) == null) {
					tt.setExecute_date("Pending");
				} else {
					tt.setExecute_date(tbarray[i].getExecute_date());
				}
				tt.setFund_id(tbarray[i].getFund_id());
				tt.setTransaction_id(tbarray[i].getTransaction_id());
				tt.setTransaction_type(tbarray[i].getTransaction_type());
				if (tt.getFund_id() == 0) {
					tt.setName("-");
					tt.setSymbol("-");
				} else {
					tt.setName(fundDAO.getFundByFundId(fundid).getName());
					tt.setSymbol(fundDAO.getFundByFundId(fundid).getSymbol());
				}
				al.add(tt);
				// System.out.println("al "+i+" "+al.get(i).getTransaction_id());
			}

			request.setAttribute("temptransactions", al); // send arraylist of
															// beans to the jsp
			Transaction.commit();
			// System.out.println(al.get(2).getTransaction_id());
			return "c_viewTransactionHistory.jsp";
		} catch (RollbackException e) {
			errors.add(e.toString());
			return "c_viewTransactionHistory.jsp";
		} catch (Exception e) {
			System.out.print("in exception" + e.getMessage());
			errors.add(e.getMessage());
			return "c_viewTransactionHistory.jsp";
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}

	}

}
