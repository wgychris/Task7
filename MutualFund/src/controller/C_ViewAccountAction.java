/**
 * 
 */
package controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.FundPriceHistoryDAO;
import model.Model;
import model.PositionDAO;
import model.TransactionDAO;

import org.genericdao.RollbackException;

import databeans.CustomerBean;
import databeans.FundPriceHistoryBean;
import databeans.PositionBean;
import databeans.TransactionBean;
import databeans.LastFundBean;

public class C_ViewAccountAction extends Action {

	// formBean needed?
	// some dao to be used
	private TransactionDAO transactionDAO;
	private PositionDAO positionDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;

	// ini DAOs
	public C_ViewAccountAction(Model model) {
		transactionDAO = model.getTransactionDAO();
		positionDAO = model.getPositionDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
	}

	@Override
	public String getName() {
		return "c_viewAccount.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		try {
			CustomerBean cb = (CustomerBean) request.getSession().getAttribute(
					"customer");
			request.setAttribute("user", cb);
			PositionBean[] pBean = positionDAO
					.getAllPositionsByCustomerIdBeans(cb.getCustomer_id());
			TransactionBean[] tbarray = transactionDAO
					.getTransactionByCustomerId(cb.getCustomer_id());
			// user is for jsp page, avoid the same name of customer form
			// session
			if (tbarray != null && tbarray.length != 0) {
				String day = tbarray[0].getExecute_date();
				for (int i = 1; i < tbarray.length; i++) {
					if (tbarray[i].getExecute_date().compareTo(day) > 0) {
						day = tbarray[i].getExecute_date();
					}
				}
				request.setAttribute("day", day);
			}
			ArrayList<LastFundBean> list = new ArrayList<LastFundBean>();
			if (pBean!= null && pBean.length != 0) {
				for (int i = 0; i < pBean.length; i++) {
					LastFundBean lfb = new LastFundBean();
					int fundID = pBean[i].getFund_id();
					lfb.setFund_id(fundID);
					lfb.setShares(pBean[i].getShares());
					FundPriceHistoryBean fphBean;
					fphBean = fundPriceHistoryDAO
							.getLastDateBeanByFundId(fundID);
					lfb.setPrice_date(fphBean.getDate());
					lfb.setPrice(fphBean.getPrice());
					list.add(lfb);
				}
			}
			System.out.print("!!!3");
			request.setAttribute("userFundList", list);
			System.out.print("return");
			return "c_viewAccount.jsp";
		} catch (RollbackException e) {
			System.out.print("e1");
			errors.add(e.toString());
			return "error.jsp";
		} catch (ParseException e) {
			System.out.print("e2");
			e.printStackTrace();
			errors.add(e.toString());
			return "error.jsp";
		}
	}

}
