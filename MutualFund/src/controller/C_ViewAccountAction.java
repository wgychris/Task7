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
			System.out.print("0: cuId:" + cb.getCustomer_id());
			PositionBean[] pBean = positionDAO
					.getAllPositionsByCustomerIdBeans(cb.getCustomer_id());
			System.out.print("1");
			TransactionBean[] tbarray = transactionDAO
					.getTransactionByCustomerId(cb.getCustomer_id());
			// user is for jsp page, avoid the same name of customer form
			// session
			System.out.print("!!!");
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
					// lfb.setName();无根据fund id查fund name的方法
					FundPriceHistoryBean fphBean;
					fphBean = fundPriceHistoryDAO
							.getLastDateBeanByFundId(fundID);
					lfb.setPrice_date(fphBean.getDate());
					lfb.setPrice(fphBean.getPrice());
				}
			}
			request.setAttribute("userFundList", list);
			return "c_viewAccount.jsp";
		} catch (RollbackException e) {
			errors.add(e.toString());
			return "error.jsp";
		} catch (ParseException e) {
			e.printStackTrace();
			errors.add(e.toString());
			return "error.jsp";
		}
	}

}
